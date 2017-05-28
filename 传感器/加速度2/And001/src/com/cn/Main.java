package com.cn;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class Main extends Activity implements SensorEventListener {
	// ����ϵͳ��Sensor������
		SensorManager sensorManager;
		EditText etTxt1;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	// ��ȡ��������ϵ��ı������
		etTxt1 = (EditText) findViewById(R.id.txt1);
		// ��ȡϵͳ�Ĵ������������
		sensorManager = (SensorManager) getSystemService(
			Context.SENSOR_SERVICE);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
	protected void onResume()
	{
		super.onResume();
		// Ϊϵͳ�ļ��ٶȴ�����ע�������
	 
		sensorManager.registerListener(this,
			sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
			SensorManager.SENSOR_DELAY_GAME);
	}
   
	protected void onStop()
	{
		// ȡ��ע��
		sensorManager.unregisterListener(this);
		super.onStop();
	}
 // ������ʵ��SensorEventListener�ӿڱ���ʵ�ֵķ���
 	// ����������ֵ�����ı�ʱ�ص��÷���
  
 	public void onSensorChanged(SensorEvent event)
 	{
 		float[] values = event.values;
 		StringBuilder sb = new StringBuilder();
 		sb.append("X�����ϵļ��ٶȣ�");
 		sb.append((int)values[0]);
 		sb.append("\nY�����ϵļ��ٶȣ�");
 		sb.append(values[1]);
 		sb.append("\nZ�����ϵļ��ٶȣ�");
 		sb.append(values[2]);
 		etTxt1.setText(sb.toString());
 	}

}