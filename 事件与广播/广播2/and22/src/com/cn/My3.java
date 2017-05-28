package com.cn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class My3 extends BroadcastReceiver {

	 
	public void onReceive(Context context, Intent intent) {
		String msg=intent.getStringExtra("msg");
		Log.i("3.", "My3===>"+msg);
		
	}
	

}
