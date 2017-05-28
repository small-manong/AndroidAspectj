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
        
           f.addAction("abc");//��̬ע����Ҫ�ڴ����ж�̬��ָ���㲥��ַ��ע��
           this.registerReceiver(c, f);
           send();
           
    }

 
	protected void onDestroy() {
		 
		super.onDestroy();
		this.unregisterReceiver(c);//��̬ע���ʱ����Ҫ�Ե����Activity��Service������ʱ���û�н��ע�ᣬϵͳ�ᱨһ���쳣����ʾ�����Ƿ����ǽ��ע���ˡ����ԣ��ǵ����ض��ĵط�ִ�н��ע������� 
	}
    public void send(){
    	Intent i=new Intent("abc");
    	  i.putExtra("msg", "hello receirver");
    	  this.sendBroadcast(i);
    }
    
}