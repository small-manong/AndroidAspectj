package com.contec.rmj.bluetooth.message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cn.com.android.tools.util.Constants;

import com.contec.rmj.bluetooth.activity.Activity_DataAquisition;
import com.contec.rmj.bluetooth.message.sp10w.CaseInfo;
import com.contec.rmj.bluetooth.message.sp10w.ParamInfo;
import com.contec.rmj.bluetooth.message.sp10w.SaveHelper;
import com.contec.rmj.bluetooth.message.sp10w.Struct_data;
import com.contec.rmj.bluetooth.spo2.PackManager;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Message_SP10W extends Message_Process {

	private int[] mOrder, mPack;
	private int[] m = { 0x00, 0x00, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40 };
	private CaseInfo mCaseInfo;
	private ParamInfo mParamInfo;
	Handler mHandler;
	ConnectedThread mConnectedThread = null;
	
	byte[] packLength = {9,9,9,9,9,6,1,1,9};

	// 病例长度低八位
	int lenLow;
	// 病例长度高八位
	int lenHigh;
	// 封装的测试数据类
	Struct_data sd;
	ArrayList<Struct_data> mDataList = new ArrayList<Struct_data>();
	
	int mIndex;
	int mSerialIndex;
	byte[] mSerial=new byte[20];
	
	FileOutputStream mFOS;
	File mSdcard = Environment.getExternalStorageDirectory();
	File mDir = new File(mSdcard, "sfcase");
	
	
	public Message_SP10W(BluetoothSocket pSocket, Handler pHandler) {
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
//									curPack = new MessagePack().decode(curPack);
//									PackManager.ProcessPack(curPack);
									processPack(unpack(curPack));
								}
							} else {
								bGetPackId = false;
								continue;
							}
						} else {
					
							if (value >= 0 && packLength[value] > 0) {
								if (value == 0x06 || value == 0x07) {
									curPack[0] = value;
									processPack(curPack);
								}
								else {
									bGetPackId = true;
									k = 0;
									len = packLength[value];
									curPack[k++] = value;
								}
								
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
		// 开始发送数据指令，向肺活量计发送此指令后，呼吸机才开始向本地发送数据
		byte cmd_start[] = { 0x40, (byte) 0x80, (byte) 0x80, (byte) 0x80,
				(byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80 };
		mConnectedThread.write(cmd_start);
		// 发送序列号
		// byte cmd_serial[] = { 0x08, (byte) 0x80, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80 };
		// mConnectedThread.write(cmd_serial);
		// 产品型号
		// byte cmd_proType[] = { 0x45, (byte) 0x80, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80 };
		// mConnectedThread.write(cmd_proType);
		// 版本控制
		// byte cmd_verControl[] = { 0x46, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80, (byte) 0x80 };
		// mConnectedThread.write(cmd_verControl);
		// 结束上传
		// byte cmd_stop[] = { 0x41, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80,
		// (byte) 0x80, (byte) 0x80 };
		// mConnectedThread.write(cmd_verControl);
		mHandler.obtainMessage(
				com.contec.rmj.bluetooth.spo2.Constants.MESSAGE_SCANFILE_OVER,
				-1, -1).sendToTarget();
	}

	public void processPack(byte[] pack) {
		Log.i("Switch", Bytes2HexString(pack, 4));
		switch (pack[0]) {
		case 0x01:
			mCaseInfo = new CaseInfo();
			mParamInfo = new ParamInfo();
			mCaseInfo.initCase(pack);
			try {
				mFOS=null;
				mFOS = new FileOutputStream(
						new File(mDir, pack[2] + 1 + ".dt"));
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			break;
		case 0x02:
			mCaseInfo.setmSmoke((byte) (pack[2] & 0x0f));
			mCaseInfo.setmDrug((byte) (pack[2] >> 4));
			mCaseInfo.setmTime(pack);
			break;
		case 0x03:
			// 得到的值要除以100.0
			mParamInfo
					.setmFvc((((pack[2] & 0xff) | ((pack[3] & 0xff) << 8)) & 0xffff) / 100.0);
			mParamInfo
					.setMfev1((((pack[4] & 0xff) | ((pack[5] & 0xff) << 8)) & 0xffff) / 100.0);
			mParamInfo
					.setmPef((((pack[6] & 0xff) | ((pack[7] & 0xff) << 8)) & 0xffff) / 100.0);
			lenLow = pack[8];
			mParamInfo
					.setmFev1Per((((pack[4] & 0xff) | (((pack[5] & 0xff) << 8))) * 100.0 / ((pack[2] & 0xff) | ((pack[3] & 0xff) << 8))));
			// 如果条件成立fvc>10.0或者pef>16.0错误的数据
			if ((((pack[2] & 0xff) | ((pack[3] & 0xff) << 8)) & 0xffff) / 100.0 > 10.0
					|| (((pack[6] & 0xff) | ((pack[7] & 0xff) << 8)) & 0xffff) / 100.0 > 16.0) {
				mCaseInfo.setmEffective((byte)0x00);
			} else {
				mCaseInfo.setmEffective((byte)0x01);
			}
			break;
		case 0x04:
			mParamInfo.setmFef25(((pack[2]&0xff) | ((pack[3]&0xff) << 8)) / 100.0);
			mParamInfo.setmFef75(((pack[4]&0xff) | ((pack[5]&0xff) << 8)) / 100.0);
			mParamInfo.setmFef2575(((pack[6]&0xff) | ((pack[7]&0xff) << 8)) / 100.0);
			lenHigh = pack[8];
			break;
		case 0x05:
			sd = new Struct_data();
			int AIRFLOW_STREN2 =pack[2]&0xff|((pack[3]&0xff)<<8)| ((pack[5]&0xff) << 24) | ((pack[4]&0xff) <<16);
			int data=AIRFLOW_STREN2-80000;
			sd.setDwData(data);
			sd.setnID(mIndex++);
			mDataList.add(sd);
			break;
		case 0x06:
			Log.i("Writing to file~~~", "ssssss");
			mCaseInfo.write(mFOS, mCaseInfo);
			char dataLen = (char) ((lenLow)&0xff |( lenHigh&0xff) << 8);
			mParamInfo.setmCaseLen(dataLen);
			try {
				mFOS.write(mSerial);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mParamInfo.write(mFOS, mParamInfo);
			int n = mDataList.size();
			SaveHelper.saveInt(mFOS, 0x00);
			for (int i = 0; i < n; i++) {
				mDataList.get(i).writeToFile(mFOS);
			}
			Log.i("DataList", n+"");
			mDataList.clear();
			mIndex=0;
			break;
		case 0x07:
			try {
				Log.i("All is finished!!!", "aaaaaaaaa");
				mFOS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 0x08:
			mSerial[mSerialIndex++]=pack[3];
			mSerial[mSerialIndex++]=pack[4];
			mSerial[mSerialIndex++]=pack[5];
			mSerial[mSerialIndex++]=pack[6];
			mSerial[mSerialIndex++]=pack[7];
			mSerial[mSerialIndex++]=pack[8];
			break;
		case 0x0b:
			mParamInfo.setmFef50(((pack[2]&0xff)|(pack[3]&0xff)<<8)/100.0);
			break;
		}
	}

	class SP10WPack {

	}

}
