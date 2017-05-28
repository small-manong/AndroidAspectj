package com.cn.zhang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG="MyReceiver";
	public void onReceive(Context context, Intent intent) {
		 String msg=intent.getStringExtra("msg");
		 Log.i(TAG, msg);
		
	}
	

}
