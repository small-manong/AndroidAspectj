package com.cn.zhang;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity {
	MyReceiver c=new MyReceiver();
    IntentFilter f=new IntentFilter();
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
           f.addAction("abc");//动态注册需要在代码中动态的指定广播地址并注册
           this.registerReceiver(c, f);
           send();
           
    }

 
	protected void onDestroy() {
		 
		super.onDestroy();
		this.unregisterReceiver(c);//动态注册的时候需要对当这个Activity或Service被销毁时如果没有解除注册，系统会报一个异常，提示我们是否忘记解除注册了。所以，记得在特定的地方执行解除注册操作： 
	}
    public void send(){
    	Intent i=new Intent("abc");
    	  i.putExtra("msg", "hello receirver");
    	  this.sendBroadcast(i);
    }
    
}