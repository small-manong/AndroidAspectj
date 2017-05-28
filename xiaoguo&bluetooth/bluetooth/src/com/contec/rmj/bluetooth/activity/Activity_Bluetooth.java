package com.contec.rmj.bluetooth.activity;

import com.contec.rmj.bluetooth.R;
import com.contec.rmj.bluetooth.service.Service_Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Bluetooth extends Activity {
	/** Called when the activity is first created. */

	// Log����
	private static final String LOG_TAG = "RMJ.Bluetooth";

	//
	private static final int REQUEST_SCAN_DEVICE = 0;
	private static final int REQUEST_ENABLE_BLUETOOTH = 1;
	private static final int REQUEST_SHOW_DATA =2;

	// ������ر���
	private BluetoothAdapter mBluetoothAdapter;
	// UI����
	private Button mScanDeviceBT;
	private Button mMakeDiscoverableBT;
	private Button mTestBT;

	public static Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// case MESSAGE_STATE_CHANGE:
			// switch (msg.arg1) {
			// case Service_Bluetooth.STATE_CONNECTED:
			// mTitle.setText(R.string.title_connected_to);
			// mTitle.append(mConnectedDeviceName);
			// mConversationArrayAdapter.clear();
			// break;
			// case Service_Bluetooth.STATE_CONNECTING:
			// mTitle.setText(R.string.title_connecting);
			// break;
			// case Service_Bluetooth.STATE_LISTEN:
			// case Service_Bluetooth.STATE_NONE:
			// mTitle.setText(R.string.title_not_connected);
			// break;
			// }
			// break;
			// case MESSAGE_WRITE:
			// byte[] writeBuf = (byte[]) msg.obj;
			// // construct a string from the buffer
			// String writeMessage = new String(writeBuf);
			// mConversationArrayAdapter.add("Me:  " + writeMessage);
			// break;
			// case MESSAGE_READ:
			// byte[] readBuf = (byte[]) msg.obj;
			// // construct a string from the valid bytes in the buffer
			// String readMessage = new String(readBuf, 0, msg.arg1);
			// mConversationArrayAdapter.add(mConnectedDeviceName + ":  "
			// + readMessage);
			// break;
			// case MESSAGE_DEVICE_NAME:
			// // save the connected device's name
			// mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
			// Toast.makeText(getApplicationContext(),
			// "Connected to " + mConnectedDeviceName,
			// Toast.LENGTH_SHORT).show();
			// break;
			// case MESSAGE_TOAST:
			// Toast.makeText(getApplicationContext(),
			// msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
			// .show();
			// break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ʼ������������
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		// ��ʼ�����水ť
		mScanDeviceBT = (Button) findViewById(R.id.scan_device_bt);
		mMakeDiscoverableBT = (Button) findViewById(R.id.make_discoverable_bt);
		mTestBT = (Button) findViewById(R.id.test_bt);
		// ���ð�ť�¼���Ӧ
		mScanDeviceBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//
				Intent _scanIntent = new Intent(Activity_Bluetooth.this,
						Activity_DeviceList.class);
				startActivityForResult(_scanIntent, REQUEST_SCAN_DEVICE);

			}
		});

		mMakeDiscoverableBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
					Intent discoverableIntent = new Intent(
							BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					discoverableIntent.putExtra(
							BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
					startActivity(discoverableIntent);
				}
			}
		});

		mTestBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_SCAN_DEVICE:
			if (resultCode == Activity.RESULT_OK) {
				// ���豸��������
				//
				// ��ת��Activity_DataAquisition,�����н����豸���Ӳ���
				Intent _intent = new Intent(Activity_Bluetooth.this,
						Activity_DataAquisition.class);
				_intent.putExtra("data", data);
				startActivityForResult(_intent, REQUEST_SHOW_DATA);
			} else {
				// �����豸ʧ��
				Log.i(LOG_TAG, "Connect Device Failed!");
				Toast.makeText(this, "Connect Device Failed!",
						Toast.LENGTH_LONG);
			}
			break;
		case REQUEST_ENABLE_BLUETOOTH:
			if (resultCode == Activity.RESULT_OK) {
				// ִ�в���
			} else {
				// ��ʾʧ��
				Log.i(LOG_TAG, "Enable Bluetooth Failed!");
				Toast.makeText(this, "Enable Bluetooth failed!",
						Toast.LENGTH_LONG);
			}
			break;
		}

	}

	@Override
	protected void onStart() {

		super.onStart();
		// ��������豸��������
		if (!mBluetoothAdapter.isEnabled()) {
			Intent _enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(_enableIntent, REQUEST_ENABLE_BLUETOOTH);
		} else {
			// ������̨����(ת��Activity_DataAquisition��ʵ��)
		}
	}

	

}