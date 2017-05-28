package com.contec.rmj.bluetooth.message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

import com.contec.rmj.bluetooth.spo2.DeviceInfo;
import com.contec.rmj.bluetooth.spo2.SaveTime_T;
import com.contec.rmj.bluetooth.spo2.SpO2PulsePack;
import com.contec.rmj.bluetooth.spo2.UserInfo;
import com.contec.rmj.bluetooth.spo2.Util;

import android.bluetooth.BluetoothSocket;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class Message_VESD extends Message_Process {

	private final byte[] UPLOAD_FILE_CONFIRM = { (byte) 0xb0, (byte) 0x00 };
	private final byte[] UPLOAD_FILE = { (byte) 0xb0, (byte) 0x00 };
	private final byte[] UPLOAD_FINISH = { (byte) 0xbf, (byte) 0xff };
	private final byte[] FILE_DATA = { (byte) 0xff, (byte) 0xff };
	private final byte[] CONFIRM_TRANSFER = { (byte) 0xff, (byte) 0xdf };

	private final byte[] ORDER_TYPE_ECG = { (byte) 0xc0, (byte) 0x00 };
	private final byte[] ORDER_TYPE_SPO2 = { (byte) 0xbf, (byte) 0x00 };
	private final byte[] ORDER_TYPE_SOUND = { (byte) 0xaf, (byte) 0x00 };

	private final int TYPE_ECG = 0;
	private final int TYPE_SPO2 = 1;
	private final int TYPE_SOUND = 2;

	private final int[][] LENGTH = { { 8, 394 }, { 8, 247 }, { 13, 0 } };

	private final int ORDER_UPLOAD = 1;
	private final int ORDER_UPLOAD_CONFIRM = 2;
	private final int ORDER_UPLOAD_FILEINFO = 3;
	private final int ORDER_UPLOAD_DATA = 4;
	private final int ORDER_UPLOAD_FINISH = 5;

	private final int[] SPO2_NUM = { 2, 9, 126 };

	static int SPO2UpLimit = 100;
	static int SPO2LowLimit = 0;
	static int PULSEUpLimit = 254;
	static int PULSELowLimit = 0;
	public static final int e_spO2_exclude = 0x7f;
	public static final int e_pulse_exclude = 0xff;

	private ConfirmThread mConfirmThread;
	private ConnectedThread mConnectedThread = null;

	public Message_VESD(BluetoothSocket pSocket) {
		super(pSocket);
		mConnectedThread = new ConnectedThread();
		mConnectedThread.start();
	}

	public byte[] pack(byte[] buffer) {
		byte[] temp = new byte[2];
		for (int i = 0; i < 2; i++)
			temp[i] = buffer[1 - i];
		return temp;
	}

	public byte[] unpack(byte[] buffer) {
		byte[] temp = new byte[2];
		for (int i = 0; i < 2; i++)
			temp[i] = buffer[1 - i];
		Log.i("Testtest", Bytes2HexString(temp, 2));
		return temp;
	}

	class ConfirmThread extends Thread {

		@Override
		public void run() {
			try {
				sleep(1000);
				write(CONFIRM_TRANSFER);
				Log.i("Confirm Transfer", Bytes2HexString(CONFIRM_TRANSFER, 2));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class ConnectedThread extends Thread {

		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			byte[] tempOrder = new byte[2];
			byte[] data = new byte[1024];
			byte temp;
			int bytes;
			boolean getOrder = true, getFileInfo = true;
			int count = 0;
			int type, dataLen = 65535, infoLen = 65535;
			int curState = 0;

			while (true) {
				try {
					bytes = mIS.read(buffer);

					for (int i = 0; i < bytes; i++) {
						data[count++] = buffer[i];
						if (count > 1 && count % 2 == 0) {
							temp = data[count - 1];
							data[count - 1] = data[count - 2];
							data[count - 2] = temp;
						}
						if (count > 1 && getOrder) {
							getOrder = false;
							switch (checkOrder(data[count - 2], data[count - 1])) {
							case ORDER_UPLOAD:
								count = 0;
								// 发确认接收消息
								write(pack(UPLOAD_FILE_CONFIRM));
								mConfirmThread = new ConfirmThread();
								mConfirmThread.start();
								curState = ORDER_UPLOAD_FILEINFO;
								break;
							case ORDER_UPLOAD_DATA:
								curState = ORDER_UPLOAD_DATA;
								break;
							case ORDER_UPLOAD_FINISH:
								curState = ORDER_UPLOAD_FINISH;
								count = 0;
								infoLen = 65535;
								dataLen = 65535;
								writeToFile(fileData);
								mConfirmThread.stop();
								break;
							default:
								Log.i("ssssss", "ssss");
								break;
							}
							
						}
						if (curState == ORDER_UPLOAD_FILEINFO && count>1) {
							type = checkType(data[0], data[1]);
							if (type != -1) {
								infoLen = LENGTH[type][0];
								dataLen = LENGTH[type][1];
								curState = ORDER_UPLOAD_FILEINFO;
							}
							if (count > infoLen) {
								fileData.add(0, data);
								count = 0;
								data = new byte[1024];
								getOrder = true;
							}

						}
						if (curState == ORDER_UPLOAD_DATA && count > dataLen) {
							fileData.add(data);
							count = 0;
							data = new byte[1024];
							getOrder = true;
						}

						// if (i % 2 == 1) {
						// //
						// if (count > 1) {
						// temp = data[count - 1];
						// data[count - 1] = data[count - 2];
						// data[count - 2] = temp;
						// }
						// if (getOrder) {
						// switch (checkOrder(buffer[i], buffer[i - 1])) {
						// case ORDER_UPLOAD:
						//
						// curState = ORDER_UPLOAD;
						// count = 0;
						// // 发确认接收消息
						// write(pack(UPLOAD_FILE_CONFIRM));
						//
						// mConfirmThread = new ConfirmThread();
						// mConfirmThread.start();
						// break;
						// case ORDER_UPLOAD_FINISH:
						// curState = ORDER_UPLOAD_FINISH;
						// count = 0;
						// infoLen = 65535;
						// dataLen = 65535;
						// writeToFile(fileData);
						// mConfirmThread.stop();
						// break;
						// }
						// getOrder = false;
						// }
						// }
						// if (curState == ORDER_UPLOAD && count > 1) {
						// // 判断文件类型
						// type = checkType(data[0], data[1]);
						// if (type != -1) {
						// infoLen = LENGTH[type][0];
						// dataLen = LENGTH[type][1];
						// curState = ORDER_UPLOAD_FILEINFO;
						// }
						// }
						//
						// if (curState == ORDER_UPLOAD_FILEINFO
						// && count > infoLen) {
						// fileData.add(0, data);
						// curState = ORDER_UPLOAD_DATA;
						// count = 0;
						// data = new byte[1024];
						// }
						// if (curState == ORDER_UPLOAD_DATA && count > dataLen)
						// {
						// fileData.add(data);
						// count = 0;
						// data = new byte[1024];
						// getOrder = true;
						// // Log.i("Data", Bytes2HexString(data,
						// // data.length));
						// }
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
		}

		public int checkOrder(byte temp1, byte temp2) {
			if (temp1 == UPLOAD_FILE[0] && temp2 == UPLOAD_FILE[1])
				return ORDER_UPLOAD;
			if (temp1 == (byte) 0xff && temp2 == (byte) 0xff)
				return ORDER_UPLOAD_DATA;
			if (temp1 == (byte) 0xbf && temp2 == (byte) 0xff)
				return ORDER_UPLOAD_FINISH;
			return 0;
		}

		public int checkType(byte temp1, byte temp2) {
			if (temp1 == ORDER_TYPE_ECG[0] && temp2 == ORDER_TYPE_ECG[1])
				return TYPE_ECG;
			if (temp1 == ORDER_TYPE_SPO2[0] && temp2 == ORDER_TYPE_SPO2[1])
				return TYPE_SPO2;
			if (temp1 == ORDER_TYPE_SOUND[0] && temp2 == ORDER_TYPE_SOUND[1])
				return TYPE_SOUND;
			return -1;
		}

	}

	public void writeToFile(LinkedList<byte[]> fileData) {
		Log.i("Write2File", "Write2File");
		String filePath = "/sdcard/contec_temp/" + "spo2" + "/SaveData/"
				+ "cmsvesd.SpO2";
		byte[] fileInfo = fileData.getFirst();
		long[] date = new long[6];
		for (int i = 0; i < 6; i++)
			date[i] = (long) fileInfo[3 + i];
		try {
			FileOutputStream mFOS = new FileOutputStream(filePath);

			byte pack[];
			UserInfo user = new UserInfo();
			user.Name("test");
			user.Age(25);
			user.Height(1700);
			user.Weight(70000);
			user.writeToFile(mFOS);
			DeviceInfo device = new DeviceInfo();
			device.PIType(1);
			device.writeToFile(mFOS);
			SaveTime_T stt = new SaveTime_T();
			// Log.i("年月日时分秒", year + "年" + month + "月" + day + "日" + hour
			// + "时" + minute + "分" + second + "秒");
			stt.SetTime(date[0], date[1], date[2], date[3], date[4], date[5]);
			stt.writeToFile(mFOS);
			int dataLen = fileData.size() - 1;
			Util.writeLong(mFOS, dataLen);
			for (int i = 0; i < dataLen; i++) {
				pack = fileData.get(i);
				Log.i("DATA", Bytes2HexString(pack, 10));
				SpO2PulsePack spp = new SpO2PulsePack();
				spp._SpO2 = (byte) ((pack[5] & 0xff) | ((pack[4] & 0xff) << 8));
				spp._Pulse = (byte) ((pack[7] & 0xff) | ((pack[6] & 0xff) << 8));
				if (spp._SpO2 > SPO2UpLimit || spp._SpO2 == SPO2LowLimit) {
					spp._SpO2 = e_spO2_exclude;
				}

				if (spp._Pulse <= PULSELowLimit || spp._Pulse > PULSEUpLimit) {
					spp._Pulse = (byte) e_pulse_exclude;
				}
				spp.writeToFile(mFOS);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
