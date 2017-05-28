package com.cn;   

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;     
import android.widget.TextView;
public class Main extends Activity    {
	 TextView myTextView1;//��ǰ�¶�
	    //SensorManager mySensorManager;//SensorManager��������
	    SensorManagerSimulator mySensorManager;//����SensorManagerSimulator����,����ʱ��
	    @Override 
	    public void onCreate(Bundle savedInstanceState) {//��дonCreate����
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);//���õ�ǰ���û�����
	        myTextView1 = (TextView) findViewById(R.id.myTextView1);//�õ�myTextView1������
	        //mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);//���SensorManager
	        //����ʱ��
	        mySensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
	        mySensorManager.connectSimulator();				//��Simulator����
	    }
	    private SensorListener mySensorListener = new SensorListener(){
	     
	    	public void onAccuracyChanged(int sensor, int accuracy) {}	//��дonAccuracyChanged����
	     
	    	public void onSensorChanged(int sensor, float[] values) {		//��дonSensorChanged����
	    		if(sensor == SensorManager.SENSOR_TEMPERATURE){//ֻ����¶ȵı仯
	    			myTextView1.setText("��ǰ���¶�Ϊ��"+values[0]);	//����ǰ�¶���ʾ��TextView
	    		}
	    	}
	    };
	    @Override
	    protected void onResume() {//��д��onResume����
	    	mySensorManager.registerListener(//ע�����
	    			mySensorListener, //������SensorListener����
	    			SensorManager.SENSOR_TEMPERATURE,//������������Ϊ�¶�
	    			SensorManager.SENSOR_DELAY_UI//�������¼����ݵ�Ƶ��
	    			);
	    	super.onResume();
	    }	
	    @Override
	    protected void onPause() {//��дonPause����
	    	mySensorManager.unregisterListener(mySensorListener);//ȡ��ע�������
	    	super.onPause();
	    }
}