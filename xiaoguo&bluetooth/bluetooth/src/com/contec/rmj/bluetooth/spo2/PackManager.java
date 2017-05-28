/**
 * fun.:	manage all operator about data packages sended/received through BT
 * author:	fengliang	2011-12-27
 */
package com.contec.rmj.bluetooth.spo2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import android.util.Log;

public class PackManager {
	public static String TAG = "PackManager";
	public static final int e_pack_username = 0x05;
	public static final int e_pack_savedate = 0x07;
	public static final int e_pack_datalen = 0x08;
	public static final int e_pack_savedatapi = 0x09;
	public static final int e_pack_pitype = 0x0E; // �Ƿ�֧��PI. 0:��PI��1����PI
	public static final int e_pack_savedata = 0x0F;
	public static final int e_pack_usernum = 0x10;
	public static final int e_pack_savetime = 0x12;
	public static final int e_pack_command = 0x7D;
	public static final int e_pack_realTimeData = 0x01;
	public static final int[] PackLength = {
			0, 9, 9, 9, 9, 9, 4, 8, 8, 6, 4, 4, 2, 3, 3, 8, // 0
			3, 9, 8, 9, 9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 1
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 2
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 3
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 4
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 5
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 6
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 0, 0  // 7
	};
	public static final String EVENT = "event notify";

	public static int mPI = 1; // ���븳ֵ
	public static int mUserNum = 0;
	public static String mUserName = null;
	public static int mDataLen = 0;
	// public static short mYear = 0;
	// public static byte mMonth = 0;
	// public static byte mDay = 0;
	// public static byte mHour = 0;
	// public static byte mMin = 0;
	// public static byte mSec = 0;

	static int SPO2UpLimit = 100;
	static int SPO2LowLimit = 0;
	static int PULSEUpLimit = 254;
	static int PULSELowLimit = 0;
	
	static String pathStr = "/sdcard/"+ "cms50ew.SpO2";
	static FileOutputStream fileTo = null;
	
	public static void init() {
		try {
			fileTo = new FileOutputStream(pathStr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// pack a unpacked package according to protocol ���Э����
	// ret: if 0: wrong. else pack's length
	public static int PackIt(byte[] buf) {
		if (buf == null) {
			return 0;
		}

		int len = PackLength[buf[0]];
		if (len > 0) {
			buf[1] = (byte) 0x80;
			for (int i = 2; i < len; ++i) {
				buf[1] |= (buf[i] & 0x80) >> (9 - i);
				buf[i] |= 0x80;
			}
		}
		return len;
	}

	// unpack a packed package, now, we only recover highest bit(bit7) of data
	// return length of the package
	public static int UnPackIt(byte[] buf) {
		int len = PackLength[buf[0]];
		for (int i = 2; i < len; ++i) {
			buf[i] &= (buf[1] << (9 - i)) | 0x7f;
		}
		return len;
	}


	public static Vector<SpO2PulsePack> m_savedata = new Vector<SpO2PulsePack>();
	public static final int e_spO2_exclude = 0x7f;
	public static final int e_pulse_exclude = 0xff;
	static SaveTime_T mSaveTime_T;
	private static UserInfo m_userInfo = new UserInfo();
	public static int mCount = 0;
	private static long mHour;
	private static long mMin;
	private static int m_pack_num;
	private static int realDataLen;
	private static long mhadupload;//用来判断数据是否已经上传 0未上传1已经上传
	// process data pack according to pack's type
	public static void ProcessPack(byte[] dataPack) {
		Log.i("________", Bytes2HexString(dataPack,1));
//		UnPackIt(dataPack);
		
		byte[] tmp4byte = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		int i = 0;
		switch (dataPack[0]) {

		case e_pack_usernum:
			//id: 0x10,  user number
			mUserNum = dataPack[2];
			break;
		case e_pack_username:
			int[] tmp = new int[6];
			for (i=0; i<6; ++i) {
				tmp[i] = dataPack[i+2];
			}
			String str = new String(tmp, 0, 6);
			mUserName = str.trim();
//			Spo250eService.mArrFileName.add(mUserName);
			break;
		case e_pack_savetime:
			// save time, hour, min, sec.
				mHour = dataPack[4];
				Log.i(Constants.TAG, mHour+"---hour");
				mMin = dataPack[5];
				Log.i(Constants.TAG, mMin+"---min");
				mhadupload = dataPack[7];
				Log.i(Constants.TAG, mhadupload+"---hadupload");
//				if (mhadupload==1) {
//				    Spo250eService.mReceiveHander.obtainMessage(3).sendToTarget();
//                }
			break;
		case e_pack_datalen:
			mDataLen = (0xff & dataPack[4]) | ((0xff & dataPack[5]) << 8)
					| ((0xff & dataPack[6]) << 16)
					| ((0xff & dataPack[7]) << 24);
			if (mPI == 0) {
				mDataLen = mDataLen / 4;
			} else {
				Log.i(TAG, "do not hava pi");
				mDataLen = mDataLen / 2;
			}
			tmp4byte[0] = (byte) (mDataLen & 0xff);
			tmp4byte[1] = (byte) ((mDataLen >> 8) & 0xff);
			tmp4byte[2] = (byte) ((mDataLen >> 16) & 0xff);
			tmp4byte[3] = (byte) ((mDataLen >> 24) & 0xff);

			Log.e(TAG, "+++ mDataLen="
					+ mDataLen + " +++");
			// 3. SAVE USER INFO
//			m_userInfo.Name("leequer");
//			m_userInfo.Age(25);
//			m_userInfo.Height(1700);
//			m_userInfo.Weight(70000);
			m_userInfo.Name("");
			m_userInfo.Age();
			m_userInfo.Height();
			m_userInfo.Weight();
			m_userInfo.writeToFile(fileTo);
			// 4. SAVE DEVICE INFO
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfo.PIType(1);
			deviceInfo.writeToFile(fileTo);
			// 5.SAVE TIME
			mSaveTime_T = new SaveTime_T();
			mSaveTime_T.SetSaveTime(mHour, mMin, 0);

			int year = 0,
			month = 0,
			day = 0;
			year = 00;
			year = year * 100 + 0;
			month = 0;
			day = 0;
			mSaveTime_T.SetSaveDate(year, month, day);
			mSaveTime_T.writeToFile(fileTo);
			//save data length
			realDataLen = mDataLen;
			Util.writeLong(fileTo, mDataLen);
//			Spo250eService.mReceiveHander.obtainMessage(0).sendToTarget();
			break;

		case e_pack_savedata:

			if (mDataLen<3) {
				m_savedata.removeAllElements();
				SpO2PulsePack one = new SpO2PulsePack();
				for (int j = 0; j < mDataLen; j++) {


					if (j == 0) {
						if (dataPack[2] > SPO2UpLimit
								|| dataPack[2] == SPO2LowLimit) {

						}else {
							one._SpO2 = dataPack[2];
							one._Pulse = dataPack[3];
						}

					}else if (j ==1) {
						if (dataPack[4] > SPO2UpLimit
								|| dataPack[4] == SPO2LowLimit) {

						}else {
							one._SpO2 = dataPack[4];
							one._Pulse = dataPack[5];
						}
					}
					m_savedata.add(one);

				}
			}
			else {
				m_savedata.get(0)._SpO2 = dataPack[2];
				m_savedata.get(0)._Pulse = dataPack[3];
				m_savedata.get(1)._SpO2 = dataPack[4];
				m_savedata.get(1)._Pulse = dataPack[5];
				m_savedata.get(2)._SpO2 = dataPack[6];
				m_savedata.get(2)._Pulse = dataPack[7];
				for (int k = 0; k < 3; k++) {
					if (m_savedata.get(k)._SpO2 > SPO2UpLimit
							|| m_savedata.get(k)._SpO2 == SPO2LowLimit) {
						m_savedata.get(k)._SpO2 = e_spO2_exclude;

					}

					if (m_savedata.get(k)._Pulse <= PULSELowLimit
							|| m_savedata.get(k)._Pulse > PULSEUpLimit) {
						m_savedata.get(k)._Pulse = (byte) e_pulse_exclude;
					}
				}
			}



			int _saveDateCount = m_savedata.size();

			for (int n = 0; n < _saveDateCount; n++) {
				m_savedata.get(n).writeToFile(fileTo);
			}
			Log.i("--------", "write to file");

			mDataLen -= 3;
			m_pack_num +=3;
			if (mDataLen <= 0) {
				try {
//					Spo250eService.mReceiveHander.obtainMessage(2).sendToTarget();
					fileTo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// add code here to notify readfile thread that current file be received
			}else {
//				Spo250eService.mReceiveHander.obtainMessage(1, m_pack_num, realDataLen).sendToTarget();
			}
			break;

		default:
			break;
		}
	}
	
	public static String Bytes2HexString(byte[] b, int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
}
