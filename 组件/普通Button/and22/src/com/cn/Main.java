package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 

public class Main extends Activity {
	 
	 
     
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		  Button btn = (Button)findViewById(R.id.btn);		//��ȡButton�ؼ�����
	        btn.setOnClickListener(new OnClickListener(){		//���OnClickListener������
		 
				public void onClick(View v) {				//��дonClick����
					Button btn = (Button)findViewById(R.id.btn);	//��ȡButton����
					btn.setBackgroundDrawable(getResources().getDrawable(R.color.btn));	//���ð�ť����
					btn.setText(R.string.btn2);			//���ð�ť��ʾ����
				}
	        });
	}

}