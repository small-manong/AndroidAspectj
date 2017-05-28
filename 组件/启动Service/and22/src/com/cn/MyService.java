package com.cn;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	MyThread my;

	public void onCreate() {
		Log.d("MyService", "onCreate");// ��ӡ��־
		super.onCreate();
	}

	public void onDestroy() {
		Log.d("MyService", "onDestroy");//��ӡ��־
		if(my!=null){
			my.flag=false;
			my=null;
		}
		super.onDestroy();
	}

	public void onStart(Intent intent, int startId) {
		Log.d("MyService", "onStart");// ��ӡ��־
		if (my == null) {
			my = new MyThread();
			my.start();
		}
		super.onStart(intent, startId);
	}

	public boolean onUnbind(Intent intent) {
		Log.d("MyService", "onUnbind");// ��ӡ��־
		if (my != null) {
			my.flag = false;
			my = null;
		}
		return super.onUnbind(intent);
	}

	public IBinder onBind(Intent arg0) {
		Log.d("MyService", "onBind");// ��ӡ��־
		if (my == null) {
			my = new MyThread();
			my.start();
		}
		return null;
	}
	

	class MyThread extends Thread {
		boolean flag = true;
		int i = 0;

		public void run() {
			while (flag) {
				Log.d("MyService", "i=" + (i++));
				try {
					Thread.sleep(1000);
				} catch (Exception e) {

				}
			}
		}
	}

}
