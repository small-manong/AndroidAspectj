package com.cn;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;
public class Main extends Activity 	implements OnGestureListener
{     
	// �������Ƽ����ʵ��
	GestureDetector detector;
	ImageView imageView;
	// ��ʼ��ͼƬ��Դ
	Bitmap bitmap;
	// ����ͼƬ�Ŀ���
	int width, height;
	// ��¼��ǰ�����ű�
	float currentScale = 1;
	// ����ͼƬ���ŵ�Matrix����
	Matrix matrix;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// �������Ƽ����
		detector = new GestureDetector(this);
		imageView = (ImageView) findViewById(R.id.show);
		matrix = new Matrix();
		//��ȡ�����ŵ�ԴͼƬ
		bitmap = BitmapFactory.decodeResource(this.getResources(),
			R.drawable.flower);
		// ���λͼ��
		width = bitmap.getWidth();
		// ���λͼ��
		height = bitmap.getHeight();
		// ����ImageView��ʼ��ʱ��ʾ��ͼƬ��
		imageView.setImageBitmap(BitmapFactory.decodeResource(
			this.getResources(), R.drawable.flower));
	}
	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		//����Activity�ϵĴ����¼�����GestureDetector����
		return detector.onTouchEvent(me);
	}
  	
	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
		float velocityX, float velocityY)
	{
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;
		//�������Ƶ��ٶ����������űȣ����velocityX>0���Ŵ�ͼ�񣬷�����Сͼ��
		currentScale += currentScale * velocityX / 4000.0f;
		//��֤currentScale�������0
		currentScale = currentScale > 0.01 ? currentScale : 0.01f;
		// ����Matrix
		matrix.reset();
		// ����Matrix
		matrix.setScale(currentScale, currentScale , 160 , 200);
		BitmapDrawable tmp = (BitmapDrawable) imageView
			.getDrawable();
		//���ͼƬ��δ���գ���ǿ�ƻ��ո�ͼƬ
		if (!tmp.getBitmap().isRecycled())             //��
		{
			tmp.getBitmap().recycle();
		}
		// ����ԭʼλͼ��Matrix������ͼƬ
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap
			, 0, 0, width, height,
			matrix, true);
		// ��ʾ�µ�λͼ
		imageView.setImageBitmap(bitmap2);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent arg0)
	{		
		return false;
	}
	@Override
	public void onLongPress(MotionEvent event)
	{
	}
	@Override
	public boolean onScroll(MotionEvent event1, MotionEvent event2,
		float distanceX, float distanceY)
	{		
		return false;
	}

	@Override
	public void onShowPress(MotionEvent event)
	{
	}
	@Override
	public boolean onSingleTapUp(MotionEvent event)
	{
		return false;
	}	
}