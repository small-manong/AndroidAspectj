package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
 

public class Main extends Activity {
	ImageView iv;				//ImageView对象引用
	Button btnNext;				//Button对象引用
	Button btnPrevious;			//Button对象引用
	Button btnAlphaPlus;		//Button对象引用
	Button btnAlphaMinus;		//Button对象引用
	int currImgId = 0;			//记录当前ImageView显示的图片id
	int alpha=255;				//记录ImageView的透明度
	int [] imgId = {			//ImageView显示的图片数组
		R.drawable.p1,	
		R.drawable.p2,
		R.drawable.p3,
		R.drawable.p4,
		R.drawable.p5,
		R.drawable.p6,
		R.drawable.p7,
		R.drawable.p8,
	};
	private OnClickListener myListener = new OnClickListener(){//自定义的OnClickListener监听器
	 
		public void onClick(View v) {		//判断点下的是那个Button
			if(v == btnNext){				//下一张图片按钮被按下
				currImgId = (currImgId+1)%imgId.length;
				iv.setImageResource(imgId[currImgId]);	//设置ImageView的显示图片
			}
			else if(v == btnPrevious){		//上一张图片按钮被按下
				currImgId = (currImgId-1+imgId.length)%imgId.length;
				
				iv.setImageResource(imgId[currImgId]);	//设置ImageView的显示图片
			}
			else if(v == btnAlphaPlus){			//增加透明度按钮被按下
				alpha -= 25;
				if(alpha < 0){
					alpha =0;
				}
				iv.setAlpha(alpha);			//设置ImageView的透明度
			}
			else if(v == btnAlphaMinus){	//减少透明度按钮被按下
				alpha += 25;
				if(alpha >255){
					alpha = 255;
				}
				iv.setAlpha(alpha);			//设置ImageView的透明度
			}
		}
	};
	           
     
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		  iv = (ImageView)findViewById(R.id.iv);			//获得ImageView对象引用
	        btnNext = (Button)findViewById(R.id.next);		//获得ImageView对象引用
	        btnPrevious = (Button)findViewById(R.id.previous);	//获得ImageView对象引用
	        btnAlphaPlus = (Button)findViewById(R.id.alpha_plus);	//获得ImageView对象引用
	        btnAlphaMinus = (Button)findViewById(R.id.alpha_minus);	//获得ImageView对象引用
	        btnNext.setOnClickListener(myListener);			//为Button对象设置OnClickListener监听器
	        btnPrevious.setOnClickListener(myListener);		//为Button对象设置OnClickListener监听器
	        btnAlphaPlus.setOnClickListener(myListener);	//为Button对象设置OnClickListener监听器
	        btnAlphaMinus.setOnClickListener(myListener);	//为Button对象设置OnClickListener监听器
	}

}