package com.cn;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	Button startService;// startService按钮
	Button stopService;// stopService按钮
	Button bindService;// bindService按钮
	Button unbindService;// unbindService按钮
	OnClickListener myOnClickListener;// 监听方法
	ServiceConnection connection;// 得到ServiceConnection引用

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		connection = new ServiceConnection() {
			public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			}

			public void onServiceDisconnected(ComponentName arg0) {
			}

		};
		startService = (Button) findViewById(R.id.startService);
		stopService = (Button) findViewById(R.id.stopService);
		bindService = (Button) findViewById(R.id.bindService);
		unbindService = (Button) findViewById(R.id.unbindService);
		myOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(Main.this, MyService.class);
				if (v == startService) {
					Main.this.startService(i);
					bindService.setEnabled(false);
					unbindService.setEnabled(false);

				} else if (v == stopService) {
					Main.this.stopService(i);
					bindService.setEnabled(!false);
					unbindService.setEnabled(!false);

				} else if (v == bindService) {
					Main.this.bindService(i, connection, Main.BIND_AUTO_CREATE);
					startService.setEnabled(false);
					stopService.setEnabled(false);

				} else if (v == unbindService) {
					Main.this.unbindService(connection);
					startService.setEnabled(!false);
					stopService.setEnabled(!false);

				}

			}

		};
		startService.setOnClickListener(myOnClickListener);
		stopService.setOnClickListener(myOnClickListener);
		bindService.setOnClickListener(myOnClickListener);
		unbindService.setOnClickListener(myOnClickListener);

	}

}