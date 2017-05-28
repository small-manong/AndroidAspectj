package com.contec.rmj.bluetooth.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Vector;

import javax.security.auth.PrivateCredentialPermission;

import cn.com.android.tools.util.Constants;

import com.contec.rmj.bluetooth.activity.Activity_Bluetooth;
import com.contec.rmj.bluetooth.activity.Activity_DataAquisition;
import com.contec.rmj.bluetooth.message.MessagePack;
import com.contec.rmj.bluetooth.message.Message_50EW;
import com.contec.rmj.bluetooth.message.Message_Process;
import com.contec.rmj.bluetooth.message.Message_SP10W;
import com.contec.rmj.bluetooth.message.Message_SXT;
import com.contec.rmj.bluetooth.message.Message_VESD;
import com.contec.rmj.bluetooth.spo2.PackManager;
import com.contec.rmj.bluetooth.spo2.ReadSpO2PackInfo;
import com.contec.rmj.bluetooth.spo2.SpO2PulsePack;

import android.R.integer;
import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Service_Bluetooth extends Service {

	private static final UUID MY_UUID_SECURE = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private final String TAG = "RMJ.BluetoothService";
	private BluetoothAdapter mAdapter;
	private Handler mHandler;
	private Message_Process mProcess;

	// ��ǰl��״̬
	public static final int STATE_NONE = 0;
	public static final int STATE_LISTEN = 1;
	public static final int STATE_CONNECTING = 2;
	public static final int STATE_CONNECTED = 3;

	public Service_Bluetooth(Context context, Handler pHandler) {
		super();
		this.mHandler = pHandler;
		PackManager.init();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);

	}

	public void connectDevice(BluetoothDevice pDevice) {
		BluetoothSocket _bSocket = null;
		// BluetoothDevice _bDevice;

		// mAdapter.cancelDiscovery();

		try {
			_bSocket = pDevice
					.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
			_bSocket.connect();

			for (int i = 0; i < 3; i++) {
				PackManager.m_savedata.add(new SpO2PulsePack());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("RMJ.BluetoothService", "Connect Device Failed!");
			try {
				_bSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.i(TAG, "Socket Close Failed!");
			}
		}

		Log.i(TAG, "Connect Device Successfully!!!");

		String mDeviceName = pDevice.getName();
		Log.i("Device Name", mDeviceName);

		if (mDeviceName.equals("SP10W")) {
			// SP10W设备连接
			mProcess = new Message_SP10W(_bSocket, mHandler);
		} else if (mDeviceName.equals("CMS50EW")) {
			// CMS50EW设备连接
			mProcess = new Message_50EW(_bSocket, mHandler);
		} else if (mDeviceName.equals("CMSVESD")) {
			// VESD设备连接
			mProcess = new Message_VESD(_bSocket);
		} else if (mDeviceName.equals("CMSSXT")) {
			// CMSSXT设备连接
			mProcess = new Message_SXT(_bSocket);
		}
		else {
			Log.i("Connected failed", "failed");
		}

//		Message_SP10W mSP10W = new Message_SP10W(_bSocket, mHandler);
		// Send the name of the connected device back to the UI Activity
		Message msg = mHandler
				.obtainMessage(Activity_DataAquisition.MESSAGE_DEVICE_NAME);
		Bundle bundle = new Bundle();
		bundle.putString(Activity_DataAquisition.DEVICE_NAME, pDevice.getName());
		msg.setData(bundle);
		mHandler.sendMessage(msg);

		// setState(STATE_CONNECTED);

	}
	
	public Message_Process getMProcess() {
		return mProcess;
	}

}
