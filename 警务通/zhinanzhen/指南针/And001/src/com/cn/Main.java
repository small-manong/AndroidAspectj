package com.cn;   

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.EventLog.Event;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
 
public class Main extends Activity
implements  SensorListener 
{
	// ������ʾָ�����ͼƬ
	ImageView znzImage;
	// ��¼ָ����ͼƬת���ĽǶ�
	float currentDegree = 0f;
	// ����ģ������Sensor������
	private SensorManagerSimulator mSensorManager;

	 // ���������Sensor������
	//SensorManager mSensorManager;
	 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������ʾָ�����ͼƬ
		znzImage = (ImageView) findViewById(R.id.znzImage);

		 // ��ȡ����Ĵ������������
		// mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		//��ȡ������ģ�����Ĵ������������
		mSensorManager = SensorManagerSimulator.getSystemService(this,
 			SENSOR_SERVICE);
		// ���Ӵ�����ģ����
		mSensorManager.connectSimulator();
	}

 
	protected void onResume()
	{
		super.onResume();
		// Ϊϵͳ�ķ��򴫸���ע�������
		mSensorManager.registerListener(Main.this,
				mSensorManager.getSensors(),
				SensorManager.SENSOR_DELAY_GAME);
	}
	 
 
	protected void onPause()
	{
		// ȡ��ע��
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	 
	protected void onStop()
	{
		// ȡ��ע��
		mSensorManager.unregisterListener(this);
		super.onStop();
	}
 
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	/*public void onSensorChanged(SensorEvent event) {

 		int sensorType =event.sensor.getType();;
		switch (sensorType)
		{
			case Sensor.TYPE_MAGNETIC_FIELD:
				// ��ȡ��Z��ת���ĽǶȡ�
				float degree = event.values[0];
				// ������ת����������ת��degree�ȣ�
				RotateAnimation ra = new RotateAnimation(currentDegree,
					-degree, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
				// ���ö����ĳ���ʱ��
				ra.setDuration(200);
				// ���ж���
				znzImage.startAnimation(ra);
				currentDegree = -degree;
				break;
		}
	}*/

	public void onSensorChanged(int sensor, float[] values) {
		// TODO Auto-generated method stub
		if (sensor == SensorManager.SENSOR_MAGNETIC_FIELD) {
		    Log.d("bugbug", "onSensorChanged: " + sensor + ", x: " + values[0]
		      + ", y: " + values[1] + ", z: " + values[2]);
		    //... ������ǶԷ�λ���ݵĴ���...
		    
		    float degree = values[0];
			// ������ת����������ת��degree�ȣ�
			RotateAnimation ra = new RotateAnimation(currentDegree,
				-degree, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
			// ���ö����ĳ���ʱ��
			ra.setDuration(200);
			// ���ж���
			znzImage.startAnimation(ra);
			currentDegree = -degree;
		   }
	}

	 
	 
}