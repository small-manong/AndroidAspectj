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
	 * �������ͼ�����Ժ󣬱����ڹ��췽����ʵ�֣�ʵ�������Ĳ���������
	 * 
	 * 
	 */
	int px,py;
	

	public MyView(Context context) {
		super(context);
		this.setFocusable(true); //�Զ�����ͼ�Ժ�������԰�ť�¼�������ʧЧ��
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
		 * ��2Dͼ��������ļ�������
		 *  1  ����һ������
		 *  2 ���û��ķ�����ȥ������ͼ��
		 *  
		 */
		Paint p=new Paint();
		 p.setARGB(90, 255, 0, 0);
		 canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), p);
		 p.setARGB(90, 255, 255, 0);
		 canvas.drawRect(px, py, px+30, py+30, p);
		 //�ڻ�һ��ʳ��϶�Ҫ����ģ�
		     
		 //�ڻ�һ�����壨��Vector ����  ���� .add()
		 
		
	}
	//�ṩ�����¼�,���߼����¼���



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Log.d("���Codeֵ",""+keyCode);
		
		
	        
		return super.onKeyDown(keyCode, event);
	}



 
	public boolean onTouchEvent(MotionEvent event) {
		    px=(int)event.getX();
		    py=(int)event.getY();
		    // �ƶ����Զ�����ͼ�����µ�λ�ó��ֵ�ʱ�򣬱����ػ�һ�βſ��ԡ�
		    this.invalidate();
		return super.onTouchEvent(event);
	}
	
	
	
	
	

}
