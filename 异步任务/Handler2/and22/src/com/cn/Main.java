package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class Main extends Activity {
	ImageView myImageView;//ImageView������
	Handler myHandler=new Handler(){//����һ��Handler����
		public void handleMessage(Message msg) {//��д������Ϣ�ķ���
			switch(msg.what){
			case 0://whatֵΪ0ʱ
				myImageView.setImageResource(R.drawable.bbta);break;
			case 1://whatֵΪ1ʱ
				myImageView.setImageResource(R.drawable.bbtb);break;
			case 2://whatֵΪ2ʱ
				myImageView.setImageResource(R.drawable.bbtc);break;
			case 3://whatֵΪ3ʱ
				myImageView.setImageResource(R.drawable.bbtd);break;	
				
			}
			super.handleMessage(msg);
		}
	};
	
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        myImageView = (ImageView) findViewById(R.id.myImageView);//�õ�ImageView������
        MyThread myThread = new MyThread(this);//��ʼ��MyThread�߳�
        myThread.start();//�����߳�
		

	}
 
 
	

}