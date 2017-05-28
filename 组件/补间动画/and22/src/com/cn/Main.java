package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
 

public class Main extends Activity {
	           
     
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		   Button btn = (Button)findViewById(R.id.btn);	//获取Button对象
		   btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				 ImageView iv = (ImageView)findViewById(R.id.iv);
				  Animation animation = AnimationUtils.loadAnimation(Main.this, R.anim.tween_ani);
			        iv.startAnimation(animation);			//启动动画			
				
			}	//为Button对象添加OnClickListener监听器
		   
		   });
	 
	}

}