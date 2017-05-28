package com.contec.rmj.bluetooth.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.com.android.tools.util.Constants;

import com.contec.rmj.bluetooth.activity.Activity_DataAquisition;
import com.contec.rmj.bluetooth.spo2.PackManager;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Message_50EW extends Message_Process {

	private int[] mOrder, mPack;
	private int[] m = { 0x00, 0x00, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40 };
	Handler mHandler;
	ConnectedThread mConnectedThread = null;

	public Message_50EW(BluetoothSocket pSocket, Handler pHandler) {
		super(pSocket);
		TAG = "Message_50EW";
		mHandler = pHandler;
		mConnectedThread = new ConnectedThread();
		mConnectedThread.start();
		mOrder = new int[9];
		mPack = new int[9];
	}

	@Override
	byte[] pack(byte[] buffer) {
		mPack[0] = buffer[0];
		mPack[1] = 0x80;

		for (int i = 2; i < 9; i++) {
			mPack[1] |= (buffer[i] & 0x80) >> (9 - i);
			mPack[i] = buffer[i] | 0x80;
		}

		return ints2bytes(mPack);
	}

	@Override
	byte[] unpack(byte[] buffer) {
		mOrder[0] = buffer[0];
		mOrder[1] = buffer[1];

		for (int i = 2; i < 9; i++) {
			if ((buffer[1] & m[i]) > 0)
				mOrder[i] = buffer[i];
			else
				mOrder[i] = buffer[i] & 0x7f;
		}

		return ints2bytes(mOrder);
	}

	private byte[] ints2bytes(int[] pArray) {
		byte[] _bArray = new byte[9];
		for (int i = 0; i < 9; i++) {
			_bArray[i] = (byte) pArray[i];
		}
		return _bArray;
	}

	private class ConnectedThread extends Thread {

		public void run() {
			byte[] buffer = new byte[1024];
			int bytes;
			boolean bGetPackId = false;
			byte[] curPack = new byte[9];
			int k = 0;
			int len = 0;
			int i;
			byte value;
			sendCommandToDevice();

			// Keep listening to the InputStream while connected
			while (true) {
				try {
					// Read from the InputStream
					bytes = mIS.read(buffer);

					// Send the obtained bytes to the UI Activity
					mHandler.obtainMessage(
							Activity_DataAquisition.MESSAGE_READ, bytes, -1,
							buffer).sendToTarget();

					for (i = 0; i < bytes; ++i) {
						value = buffer[i];
						if (bGetPackId) {
							if (value < 0) {
								curPack[k++] = value;
								if (k >= len) {
									bGetPackId = false;
									// 需要处理该包
									curPack = new MessagePack().decode(curPack);
									PackManager.ProcessPack(curPack);
								}
							} else {
								bGetPackId = false;
								continue;
							}
						} else {
							if (value >= 0 && PackManager.PackLength[value] > 0) {
								bGetPackId = true;
								k = 0;
								len = PackManager.PackLength[value];
								curPack[k++] = value;
							}
						}
					}
				} catch (IOException e) {
					Log.e(TAG, "disconnected", e);
					connectionLost();
					break;
				}
			}
		}

		public void write(byte[] buffer) {
			try {
				mOS.write(buffer);

				// ����UI����ʾ���͵�ָ��
				mHandler.obtainMessage(Activity_DataAquisition.MESSAGE_WRITE,
						-1, -1, buffer).sendToTarget();
				Log.i("Send Message",
						"Send Message: "
								+ String.format("%x %x %x %x %x %x %x %x %x",
										buffer[0], buffer[1], buffer[2],
										buffer[3], buffer[4], buffer[5],
										buffer[6], buffer[7], buffer[8]));
			} catch (IOException e) {
				Log.e(TAG, "Exception during write", e);
			}
		}

		public int[] byte2int(byte[] byteArray) {
			int[] temp = new int[1024];
			for (int i = 0; i < 1024; i++) {
				temp[i] = byteArray[i];
			}
			return temp;
		}

		public void cancel() {
			try {
				mSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "connected socket close failed", e);
			}
		}
	}

	private void connectionLost() {
		// ����l��ʧ����Ϣ��Activity_DataAquisition
		Message msg = mHandler
				.obtainMessage(Activity_DataAquisition.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(Activity_DataAquisition.TOAST,
				"Device connection was lost");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	protected void sendCommandToDevice() {
		Log.e(Constants.TAG, "+++ ScanFileThread.run() +++");
		byte comStopSendRealTimeData[] = { (byte) 0x7d, (byte) 0x80,
				(byte) 0xb0, (byte) 0x00, (byte) 0x00, (byte) 0x00,
				(byte) 0x00, (byte) 0x00, (byte) 0x00 };
		// PackManager.PackIt(comStopSendRealTimeData);
		write(comStopSendRealTimeData);

		try {
			Thread.sleep(200); // wait for return;
		} catch (InterruptedException e) {
			return;
		}

		byte comd_pi[] = { (byte) 0x7d, (byte) 0x81, (byte) 0xAC, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		// PackManager.PackIt(comd_pi);
		write(comd_pi); // 0xAC: request pi

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Log.e(Constants.TAG, "+++ wrong in wait user number+++");
			return;
		}
		byte comd_pi1[] = { (byte) 0x7d, (byte) 0x81, (byte) 0xAD, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		// PackManager.PackIt(comd_pi1);
		write(comd_pi1); // 0xAC: request pi

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Log.e(Constants.TAG, "+++ wrong in wait user number+++");
			return;
		}
		byte comd_pi2[] = { (byte) 0x7d, (byte) 0x81, (byte) 0xAB, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		// PackManager.PackIt(comd_pi2);
		write(comd_pi2); // 0xAC: request pi

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Log.e(Constants.TAG, "+++ wrong in wait user number+++");
			return;
		}
		byte comd_pi3[] = { (byte) 0x7d, (byte) 0x81, (byte) 0xA5, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		// PackManager.PackIt(comd_pi3);
		write(comd_pi3); // 0xAC: request pi

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Log.e(Constants.TAG, "+++ wrong in wait user number+++");
			return;
		}
		byte comd_pi4[] = { (byte) 0x7d, (byte) 0x81, (byte) 0xA4, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		// PackManager.PackIt(comd_pi4);
		write(comd_pi4); // 0xAC: request pi

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Log.e(Constants.TAG, "+++ wrong in wait user number+++");
			return;
		}
		byte comd_pi5[] = { (byte) 0x7d, (byte) 0x81, (byte) 0xA6, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		// PackManager.PackIt(comd_pi5);
		write(comd_pi5); // 0xAC: request pi

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Log.e(Constants.TAG, "+++ wrong in wait user number+++");
			return;
		}

		Log.e(Constants.TAG, "+++ scan file ++=" + PackManager.mUserNum);
		mHandler.obtainMessage(
				com.contec.rmj.bluetooth.spo2.Constants.MESSAGE_SCANFILE_OVER,
				-1, -1).sendToTarget();
	}

	private void processPack(byte[] pack) {
		
	}
	
	class CMS50EWPack {
		
	}
}
