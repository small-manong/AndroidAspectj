package com.contec.rmj.bluetooth.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

public abstract class Message_Process {
	BluetoothSocket mSocket;
	InputStream mIS;
	OutputStream mOS;
	Handler mHandler;
	LinkedList<byte[]> fileData;
	String TAG;
	
	public Message_Process(BluetoothSocket pSocket) {
		this.mSocket = pSocket;
		fileData = new LinkedList<byte[]>();
		try {
			mIS = mSocket.getInputStream();
			mOS = mSocket.getOutputStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	abstract byte[] pack(byte[] buffer);
	abstract byte[] unpack(byte[] buffer);

	public void write(byte[] buffer) {
		try {
			mOS.write(buffer);
			Log.i(">>>>>>>>>>>>>>>>", Bytes2HexString(buffer, buffer.length));
			mOS.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String Bytes2HexString(byte[] b, int length) {
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
