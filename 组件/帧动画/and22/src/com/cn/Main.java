package com.cn;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
 

public class Main extends Activity {
	           

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		   Button btn = (Button)findViewById(R.id.btn);
		   btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				   ImageView iv = (ImageView)findViewById(R.id.iv);
				   iv.setBackgroundResource(R.anim.frame_ani);
			        AnimationDrawable ad = (AnimationDrawable)iv.getBackground();
			        ad.start();		//启动AnimationDrawable
				
			}	//为按钮设置监听器
			   
		   });
	 
	}

}