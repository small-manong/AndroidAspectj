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
	// 定义显示指南针的图片
	ImageView znzImage;
	// 记录指南针图片转过的角度
	float currentDegree = 0f;
	// 定义模拟器的Sensor管理器
	private SensorManagerSimulator mSensorManager;

	 // 定义真机的Sensor管理器
	//SensorManager mSensorManager;
	 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取界面中显示指南针的图片
		znzImage = (ImageView) findViewById(R.id.znzImage);

		 // 获取真机的传感器管理服务
		// mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		//获取传感器模拟器的传感器管理服务
		mSensorManager = SensorManagerSimulator.getSystemService(this,
 			SENSOR_SERVICE);
		// 连接传感器模拟器
		mSensorManager.connectSimulator();
	}

 
	protected void onResume()
	{
		super.onResume();
		// 为系统的方向传感器注册监听器
		mSensorManager.registerListener(Main.this,
				mSensorManager.getSensors(),
				SensorManager.SENSOR_DELAY_GAME);
	}
	 
 
	protected void onPause()
	{
		// 取消注册
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	 
	protected void onStop()
	{
		// 取消注册
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
				// 获取绕Z轴转过的角度。
				float degree = event.values[0];
				// 创建旋转动画（反向转过degree度）
				RotateAnimation ra = new RotateAnimation(currentDegree,
					-degree, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
				// 设置动画的持续时间
				ra.setDuration(200);
				// 运行动画
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
		    //... 下面就是对方位数据的处理...
		    
		    float degree = values[0];
			// 创建旋转动画（反向转过degree度）
			RotateAnimation ra = new RotateAnimation(currentDegree,
				-degree, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
			// 设置动画的持续时间
			ra.setDuration(200);
			// 运行动画
			znzImage.startAnimation(ra);
			currentDegree = -degree;
		   }
	}

	 
	 
}