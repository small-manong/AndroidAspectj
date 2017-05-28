package com.cn;
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;
public class Main extends Activity 	implements OnGestureListener
{
	// �������Ƽ����ʵ��
	GestureDetector detector;	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//�������Ƽ����
		detector = new GestureDetector(this);		
	}
	//����Activity�ϵĴ����¼�����GestureDetector����
	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		return detector.onTouchEvent(me);
	}	
	@Override
	public boolean onDown(MotionEvent arg0)
	{
		Toast.makeText(this,"onDown" , 8000)
			.show();
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY)
	{
		Toast.makeText(this , "onFling" , 8000)
			.show();
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e)
	{
		Toast.makeText(this ,"onLongPress" , 8000)
			.show();		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
		float distanceY)
	{
		Toast.makeText(this ,"onScroll" , 8000)
			.show();	
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e)
	{
		Toast.makeText(this ,"onShowPress" , 8000)
			.show();		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		Toast.makeText(this ,"onSingleTapUp" , 8000)
			.show();
		return false;
	}
}