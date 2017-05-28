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
		this.unregisterReceiver(m1);//��̬ע���ʱ����Ҫ�Ե����Activity��Service������ʱ���û�н��ע�ᣬϵͳ�ᱨһ���쳣����ʾ�����Ƿ����ǽ��ע���ˡ����ԣ��ǵ����ض��ĵط�ִ�н��ע�������
		this.unregisterReceiver(m2);
		this.unregisterReceiver(m3);
	}
  public void send(){
  	Intent i=new Intent("abc");
  	  i.putExtra("msg", "hello ZhangXueFeng");
  	  this.sendOrderedBroadcast(i, "scott.permission.MY_BROADCAST_PERMISSION");
  	  
  }
	

}
