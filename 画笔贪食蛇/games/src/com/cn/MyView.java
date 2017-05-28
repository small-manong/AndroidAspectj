package com.cn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View  {//ScrollView  ?
	/*
	 * 定义好视图对象以后，必须在构造方法内实现，实现上下文参数的引用
	 * 
	 * 
	 */
	int px,py;
	

	public MyView(Context context) {
		super(context);
		this.setFocusable(true); //自定义视图以后，你的所以按钮事件，都会失效。
	}
	
	

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setFocusable(true); 
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/*
		 * 画2D图像必须做的几个步骤
		 *  1  定义一个画笔
		 *  2 调用画的方法，去画几何图像
		 *  
		 */
		Paint p=new Paint();
		 p.setARGB(90, 255, 0, 0);
		 canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), p);
		 p.setARGB(90, 255, 255, 0);
		 canvas.drawRect(px, py, px+30, py+30, p);
		 //在画一个食物（肯定要随机的）
		     
		 //在画一个身体（）Vector 数组  对象 .add()
		 
		
	}
	//提供触摸事件,或者键盘事件。



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Log.d("你的Code值",""+keyCode);
		
		
	        
		return super.onKeyDown(keyCode, event);
	}



 
	public boolean onTouchEvent(MotionEvent event) {
		    px=(int)event.getX();
		    py=(int)event.getY();
		    // 移动的自定义视图，在新的位置出现的时候，必须重绘一次才可以。
		    this.invalidate();
		return super.onTouchEvent(event);
	}
	
	
	
	
	

}
