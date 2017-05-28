package com.contec.rmj.bluetooth.activity;

import java.util.ArrayList;

import cn.com.android.tools.util.Constants;

import com.contec.rmj.bluetooth.R;
import com.contec.rmj.bluetooth.message.MessagePack;
import com.contec.rmj.bluetooth.service.Service_Bluetooth;
import com.contec.rmj.bluetooth.spo2.PackManager;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_DataAquisition extends Activity {

	//
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	//
	// public static final int[] REQUEST_ORDER_TEST = { 0x7d, 0x80, 0xa2, 0x00,
	// 0x00, 0x00, 0x00, 0x00, 0x00 };
	public static final int[] REQUEST_ORDER_TEST = { 0x7d, 0x81, 0xa1, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00 };

	//
	public static final byte[] CMD_TEST = { 0x53, 0x4e, 0x06, 0x00, 0x02, 0x05,
			0x00, 0x00, 0x0d };
	public static final byte[] CMD_RESULT = { 0X53, 0x4E, 0x06, 0x00, 0x02,
			0x04, 0x00, 0x00, 0x0c };
	public static final byte[] CMD_RESULTS = { 0X53, 0x4E, 0x06, 0x00, 0x02,
			0x05, 0x00, 0x00, 0x0D };
	public static final byte[] CMD_SETDATE = { 0X53, 0x4E, 0x09, 0x00, 0x02,
			0x06, 0x0B, 0x03, 0x18, 0x0E, 0x1A, 0x5F };
	public static final byte[] CMD_GETID = { 0X53, 0x4E, 0x06, 0x00, 0x02,
			0x07, 0x00, 0x00, 0x0F };
	public static final byte[] CMD_CLEARDATA = { 0X53, 0x4E, 0x06, 0x00, 0x02,
			0x08, 0x00, 0x00, 0x10 };
	public static final byte[] CMD_CODE = { 0X53, 0x4E, 0x06, 0x00, 0x02, 0x09,
			0x00, 0x05, 0x16 };

	//
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	private ArrayAdapter<String> mConversationArrayAdapter;

	private ListView mConversationView;
	private Button mTestBT, bt1, bt2, bt3, bt4, bt5, bt6;

	private BluetoothAdapter mBluetoothAdapter;
	private String mAddress;
	private Service_Bluetooth mBService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_aquisition);
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mBService = new Service_Bluetooth(this, mHandler);

		mConversationArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.message);
		mConversationView = (ListView) findViewById(R.id.in);
		mConversationView.setAdapter(mConversationArrayAdapter);

		mTestBT = (Button) findViewById(R.id.test_send_bt);
		mTestBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// sendCommandToDevice();
				// int[] buffer;
				// buffer = new MessagePack().encode(REQUEST_ORDER_TEST);
				// sendMessage(int2byte(buffer));
				// Log.i("---------------------",
				// Bytes2HexString(int2byte(buffer), 9));
				mBService.getMProcess().write(CMD_TEST);

			}
		});

		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		bt3 = (Button) findViewById(R.id.button3);
		bt4 = (Button) findViewById(R.id.button4);
		bt5 = (Button) findViewById(R.id.button5);
		bt6 = (Button) findViewById(R.id.button6);

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBService.getMProcess().write(CMD_RESULT);
			}
		});
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBService.getMProcess().write(CMD_RESULTS);
			}
		});
		bt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBService.getMProcess().write(CMD_SETDATE);
			}
		});
		bt4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBService.getMProcess().write(CMD_GETID);
			}
		});
		bt5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBService.getMProcess().write(CMD_CLEARDATA);
			}
		});
		bt6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBService.getMProcess().write(CMD_CODE);
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		connectDevice(getIntent());
	}

	private void connectDevice(Intent data) {
		// TODO Auto-generated method stub
		Intent _intent = (Intent) getIntent().getExtras().get("data");
		mAddress = _intent.getExtras().getString(
				Activity_DeviceList.EXTRA_DEVICE_ADDRESS);
		BluetoothDevice _device = mBluetoothAdapter.getRemoteDevice(mAddress);
		mBService.connectDevice(_device);

	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// ����ȡ���������ʾ��4
				// String readMessage = new String(readBuf, 0, msg.arg1);
				String readMessage = Bytes2HexString(readBuf, msg.arg1);
				// Log.i("Read Message", readMessage);
				mConversationArrayAdapter.add(readMessage);
				break;
			}
		}

	};

	// private void sendMessage(byte[] send) {
	// mBService.write(send);
	// }

	private String Bytes2HexString(byte[] b, int length) {
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

	// protected void sendCommandToDevice() {
	// // TODO Auto-generated method stub
	// Log.e(Constants.TAG, "+++ ScanFileThread.run() +++");
	// byte comStopSendRealTimeData[] = { (byte) PackManager.e_pack_command,
	// (byte) 0x80, (byte) 0x0C, (byte) 0x00, (byte) 0x00,
	// (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
	// PackManager.PackIt(comStopSendRealTimeData);
	// mBService.write(comStopSendRealTimeData);
	//
	// try {
	// Thread.sleep(200); // wait for return;
	// } catch (InterruptedException e) {
	// // BluetoothListActivity.mHandler.obtainMessage(13).sendToTarget();
	// return;
	// }
	//
	// //mArrFileName = new ArrayList<String>(5);
	//
	// // request whether exist pi. //0xAC: request pi
	// byte comd_pi[] = { (byte) PackManager.e_pack_command, (byte) 0x80,
	// (byte) 0xAC, (byte) 0x00, (byte) 0x00, (byte) 0x00,
	// (byte) 0x00, (byte) 0x00, (byte) 0x00 };
	// PackManager.PackIt(comd_pi);
	// mBService.write(comd_pi); // 0xAC: request pi
	//
	// // get amount of user //0xAD: request user number
	// byte comd_user[] = { (byte) PackManager.e_pack_command, (byte) 0x80,
	// (byte) 0xAD, (byte) 0x00, (byte) 0x00, (byte) 0x00,
	// (byte) 0x00, (byte) 0x00, (byte) 0x00 };
	// PackManager.PackIt(comd_user);
	// mBService.write(comd_user); // 0xAD: request user number
	//
	// // wait for command return.
	// try {
	// // PackManager.EVENT.wait();
	// Thread.sleep(200);
	// } catch (InterruptedException e) {
	// Log.e(Constants.TAG, "+++ wrong in wait user number+++");
	// // BluetoothListActivity.mHandler.obtainMessage(13).sendToTarget();
	// return;
	// }
	//
	// // here we know total user number, then, we request user's info
	// int i;
	// for (i = 0; i < PackManager.mUserNum; ++i) {
	// byte comdt[] = { (byte) PackManager.e_pack_command, (byte) 0x80,
	// (byte) 0xAB, (byte) i, (byte) 0x00, (byte) 0x00,
	// (byte) 0x00, (byte) 0x00, (byte) 0x00 };
	// PackManager.PackIt(comdt);
	// mBService.write(comdt); // request user name
	// try {
	// // PackManager.mUserName.wait();
	// Thread.sleep(200);
	// if (PackManager.mUserNum == 0) {
	// // BluetoothListActivity.mHandler.obtainMessage(13)
	// // .sendToTarget();
	// return;
	// }
	// } catch (InterruptedException e) {
	// Log.e(Constants.TAG, "+++ wrong in get username +++");
	// // BluetoothListActivity.mHandler.obtainMessage(13).sendToTarget();
	// return;
	// }
	// }
	// Log.e(Constants.TAG, "+++ scan file ++=" + PackManager.mUserNum);
	// // here, we have scanned all files, so we notify main thread to display
	// // it
	// mHandler.obtainMessage(com.contec.rmj.bluetooth.spo2.Constants.MESSAGE_SCANFILE_OVER,
	// -1, -1).sendToTarget();
	// }

}
