package com.contec.rmj.bluetooth.message;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import com.contec.rmj.bluetooth.message.sxt.SXTData;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

/**
 * 联系人：张工 15116466529 PIN码：1234
 * 
 * @author Administrator
 * 
 */
public class Message_SXT extends Message_Process {

	LinkedList<SXTData> dataList = new LinkedList<SXTData>();
	int dataLen=0;
	SXTData data;
	byte[] params;
	
	public Message_SXT(BluetoothSocket pSocket) {
		super(pSocket);
		TAG = "Message_SXT";
		ConnectedThread mConnectedThread = new ConnectedThread();
		mConnectedThread.start();
		// TODO Auto-generated constructor stub
	}

	@Override
	byte[] pack(byte[] buffer) {
		return null;
	}

	@Override
	byte[] unpack(byte[] buffer) {
		return null;
	}

	class ConnectedThread extends Thread {

		byte[] buffer = new byte[1024];
		int bytes, count = 0;
		byte[] pack = new byte[1024];
		byte length;
//		byte[] test = { 0x53, 0x4e, 0x08, 0x00, 0x02, 0x01, 0x53, 0x49, 0x4e,
//				0x4f, 0x44 };
		byte[] test = {0x53, 0x4e, 0x06, 0x00, 0x02, 0x05, 0x00, 0x00, 0x0d};
//		0X53 4E 06 00 02 05 00 00 0D

		@Override
		public void run() {
			try {
				mOS.write(test);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			while (true) {
				try {
					bytes = mIS.read(buffer);

					for (int i = 0; i < bytes; i++) {
						pack[count++] = buffer[i];
						if (count == 3) {
							length = buffer[i];
						}
						if (count > 2 && count > length + 2) {
							Log.i("Received Message:", Bytes2HexString(pack, count));
							processPack(pack);
							count = 0;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}

			}
		}

	}

	public void processPack(byte[] pack) {
		SXTPack sxtPack = new SXTPack(pack);
		switch (sxtPack.getCmd()) {
		case 0x01:
			Log.i(TAG, Bytes2HexString(pack, sxtPack.getLength() + 3));
			break;
		case 0x02:
			Log.i(TAG, "错误信息：" + Bytes2HexString(sxtPack.getParams(), 2)
					+ "    机器代码：" + Bytes2HexString(sxtPack.getDeviceCode(), 2));
			break;
		case 0x03:
			//滴血符号闪烁	
			break;
		case 0x04:
			data = new SXTData(sxtPack.getParams());
			Log.i(TAG, Bytes2HexString(sxtPack.getParams(), 8));
			break;
		case 0x05:
			params = sxtPack.getParams();
			//数据为空
			if (params[0] == 0) {
			}
			else if (sxtPack.getParams()[1]-params[0]>0) {
				//
//				data = new SXTData(sxtPack.getParams());
				for (int i = 0; i < params[2]; i++) {
					//data = new SXTData(Arrays.copyOfRange(params, i*8, i*8+7));
					//dataList.add(data);
				}
			}else if (sxtPack.getParams()[1]-sxtPack.getParams()[0]==0) {
				//最后一个数据包
//				data = new SXTData(sxtPack.getParams());
				for (int i = 0; i < params[2]; i++) {
					//data = new SXTData(Arrays.copyOfRange(params, i*8, i*8+7));
					//dataList.add(data);
				}
				//显示记录
				Log.i(TAG, "-----------------------------");
				for (int i = 0; i < dataList.size(); i++) {
					Log.i(TAG, Bytes2HexString(dataList.get(i).getParams(), 8));
				}
				Log.i(TAG, "-----------------------------");
			}
			break;
		case 0x06:
//			byte[] time = Arrays.copyOfRange(sxtPack.getParams(), 7, 11);
//			Log.i(TAG, "当前时间:"+Bytes2HexString(time, 5));
			break;
		case 0x07:
			//byte[] deviceID = Arrays.copyOfRange(sxtPack.getParams(), 0, 5);
			//byte[] num = Arrays.copyOfRange(sxtPack.getParams(), 5, 7);
			//byte[] time = Arrays.copyOfRange(sxtPack.getParams(), 7, 12);
			//Log.i(TAG, "机器编号:"+Bytes2HexString(deviceID, 5)+"生产的第："+Bytes2HexString(num, 2)+"台"+"    当前时间："+Bytes2HexString(time, 5));
			break;
		case 0x08:
			if(sxtPack.getParams()[1]==1) {
				Log.i(TAG, "清空数据成功");
			}
			else Log.i(TAG, "清空数据失败");
			break;
		case 0x09:
			if(sxtPack.getParams()[0]==0) {
				Log.i(TAG, "修改校正码成功");
			}
			else Log.i(TAG, "修改校正码失败");
			break;
		case 0x0a:
			Log.i(TAG, "开始测试");
			break;
		case 0x0b:
			Log.i(TAG, "设备已关机");
			break;
		}

	}

	class SXTPack {
		byte[] header, deviceCode, params;
		byte length, cmd, checksum;

		public SXTPack(byte[] pack) {
			header = new byte[2];
			header[0] = pack[0];
			header[1] = pack[1];
			length = pack[2];
			deviceCode = new byte[2];
			deviceCode[0] = pack[3];
			deviceCode[1] = pack[4];
			cmd = pack[5];
			params = new byte[length];
			for (int i = 0; i < length -3; i++) {
				if (i < length-4)
					params[i] = pack[6 + i];
				else
					checksum = pack[6 + i];
			}
		}

		public byte[] getHeader() {
			return header;
		}

		public byte[] getDeviceCode() {
			return deviceCode;
		}

		public byte[] getParams() {
			return params;
		}

		public byte getLength() {
			return length;
		}

		public byte getCmd() {
			return cmd;
		}

		public byte getChecksum() {
			return checksum;
		}

	}

}
