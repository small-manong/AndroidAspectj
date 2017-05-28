package com.contec.rmj.bluetooth.message.sp10w;

import java.io.IOException;
import java.io.OutputStream;

public class SaveHelper {
	public static void saveInt(OutputStream out, int data) {
//		�����ʽ��ԓ�ǵ�λ��ǰ����λ�ں�������ķ������棬�M��Ҫ��
		byte b[] = new byte[4];
		b[0] = (byte) (data & 0xff);
		b[1] = (byte) ((data >> 8) & 0xff);
		b[2] = (byte) ((data >> 16) & 0xff);
		b[3] = (byte) ((data >> 24) & 0xff);
		try {
			out.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveByteArray(OutputStream out, byte[] data) {
		try {
			out.write(data);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
//	double���͵ı��淽��:��double��ת��Ϊ������,Ȼ���ð˸��ֽڱ��泤����(ͨ��λ�Ʋ���)
	public static void saveDouble(OutputStream out, double data) {
		byte[] arr=new byte[8];
		 Long l = Double.doubleToLongBits(data);
		for(int i=0;i<8;i++){
			arr[i]=(byte) ((l>>(8*i)));
		}
		try {
			out.write(arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveByte(OutputStream out, int data) {
		try {
			out.write(data);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveString(OutputStream out, String data, int i) {
		byte[] b = new byte[i];
		if (data != null) {
			byte[] bData = data.getBytes();
			int n = bData.length;
			int m = b.length;
			if (m >= n) {
				for (int j = 0; j < n; j++) {
					b[j] = bData[j];
				}
			} else {
				for (int j = 0; j < m; j++) {
					b[j] = bData[j];
				}
			}
		}
		try {
			out.write(b, 0, i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveChar(OutputStream out, char pdata) {
		byte[] _pdata=new byte[4];
		_pdata[0]=(byte) (pdata>>8&0xff);
		_pdata[1]=(byte) (pdata&0xff);
		try {
			out.write(_pdata);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
