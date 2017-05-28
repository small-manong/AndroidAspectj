package com.cn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class My1 extends BroadcastReceiver {

 
	public void onReceive(Context context, Intent intent) {
	   String msg=intent.getStringExtra("msg");
	   Log.i("1.","my1===>"+msg);
	   
	   Bundle bundle = new Bundle();  
       bundle.putString("msg", msg + "@FirstReceiver");  
       setResultExtras(bundle);  

       abortBroadcast(); 
	}

}
