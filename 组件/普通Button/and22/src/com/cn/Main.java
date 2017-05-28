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
		  Button btn = (Button)findViewById(R.id.btn);		//获取Button控件对象
	        btn.setOnClickListener(new OnClickListener(){		//添加OnClickListener监听器
		 
				public void onClick(View v) {				//重写onClick方法
					Button btn = (Button)findViewById(R.id.btn);	//获取Button对象
					btn.setBackgroundDrawable(getResources().getDrawable(R.color.btn));	//设置按钮背景
					btn.setText(R.string.btn2);			//设置按钮显示文字
				}
	        });
	}

}