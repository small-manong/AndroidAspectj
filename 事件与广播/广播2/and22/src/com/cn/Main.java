package com.cn;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class Main extends Activity {
 
    My1 m1=new My1();
    My2 m2=new My2();
    My3 m3=new My3();
    IntentFilter f=new IntentFilter();
    
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      f.addAction("abc");
      this.registerReceiver(m1, f);
      this.registerReceiver(m2, f);
      this.registerReceiver(m3, f);
      send();
      
  }
  protected void onDestroy() {
		 
		super.onDestroy();
		this.unregisterReceiver(m1);//动态注册的时候需要对当这个Activity或Service被销毁时如果没有解除注册，系统会报一个异常，提示我们是否忘记解除注册了。所以，记得在特定的地方执行解除注册操作：
		this.unregisterReceiver(m2);
		this.unregisterReceiver(m3);
	}
  public void send(){
  	Intent i=new Intent("abc");
  	  i.putExtra("msg", "hello ZhangXueFeng");
  	  this.sendOrderedBroadcast(i, "scott.permission.MY_BROADCAST_PERMISSION");
  	  
  }
	

}
