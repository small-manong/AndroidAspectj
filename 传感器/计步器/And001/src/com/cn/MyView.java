package com.cn;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
public class MyView extends View{
	Paint paint;//�������ʵ�����
	Bitmap big;//��Բ��ͼƬ
	Bitmap small;//Сˮ�ݵ�ͼƬ
	int big_X = 33;//��Բ��x����
	int big_Y = 33;//��Բ��y����
	int small_X;//Сˮ�ݵ�x����
	int small_Y;//Сˮ�ݵ�y����
	public MyView(Context context, AttributeSet attrs) {//������
		super(context, attrs);
		init();//��ʼ����Դ
	}
	public void init(){//��ʼ������
		paint = new Paint();//��ʼ������
		big = BitmapFactory.decodeResource(getResources(), R.drawable.big);//��ʼ����Բ��ͼƬ
		small = BitmapFactory.decodeResource(getResources(), R.drawable.small);//��ʼ��ˮ�ݵ�ͼƬ
		small_X = big_X + big.getWidth()/2 - small.getWidth()/2;//��ʼ��ˮ�ݵ�x���� 
		small_Y = big_Y + big.getHeight()/2 - small.getHeight()/2;//��ʼ��ˮ�ݵ�y����
	}
	@Override
	protected void onDraw(Canvas canvas) {//���Ʒ���
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);//���ñ���ɫΪ��ɫ
		paint.setColor(Color.BLUE);//���û�����ɫ
		paint.setStyle(Style.STROKE); //���û���Ϊ�����
		canvas.drawRect(5, 5, 315, 315, paint);//����߿�
		canvas.drawBitmap(big, big_X, big_Y, paint);//���ƴ�Բ 
		canvas.drawBitmap(small, small_X, small_Y, paint);//����ˮ��	
		
		RectF oval = new RectF(big_X+big.getWidth()/2-10, 
				big_Y+big.getHeight()/2-10, 
				big_X+big.getWidth()/2+10,
				big_Y+big.getHeight()/2+10);
		canvas.drawOval(oval, paint);//���ƻ�׼��(Բ)
	}	
}


