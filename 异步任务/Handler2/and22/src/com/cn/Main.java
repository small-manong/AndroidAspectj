package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class Main extends Activity {
	ImageView myImageView;//ImageView的引用
	Handler myHandler=new Handler(){//创建一个Handler对象
		public void handleMessage(Message msg) {//重写接收消息的方法
			switch(msg.what){
			case 0://what值为0时
				myImageView.setImageResource(R.drawable.bbta);break;
			case 1://what值为1时
				myImageView.setImageResource(R.drawable.bbtb);break;
			case 2://what值为2时
				myImageView.setImageResource(R.drawable.bbtc);break;
			case 3://what值为3时
				myImageView.setImageResource(R.drawable.bbtd);break;	
				
			}
			super.handleMessage(msg);
		}
	};
	
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        myImageView = (ImageView) findViewById(R.id.myImageView);//得到ImageView的引用
        MyThread myThread = new MyThread(this);//初始化MyThread线程
        myThread.start();//启动线程
		

	}
 
 
	

}