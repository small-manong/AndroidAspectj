package com.cn;   

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
public class Main extends Activity    {
	   TextView myTextView1;//x����ļ��ٶ�
	    TextView myTextView2;//y����ļ��ٶ�
	    TextView myTextView3;//z����ļ��ٶ�
	    //SensorManager mySensorManager;//SensorManager��������
	    SensorManagerSimulator mySensorManager;//����SensorManagerSimulator����,����ʱ��
	    @Override 
	    public void onCreate(Bundle savedInstanceState) {//��дonCreate����
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);//���õ�ǰ���û�����
	        myTextView1 = (TextView) findViewById(R.id.myTextView1);//�õ�myTextView1������
	        myTextView2 = (TextView) findViewById(R.id.myTextView2);//�õ�myTextView2������
	        myTextView3 = (TextView) findViewById(R.id.myTextView3);//�õ�myTextView3������
	        //mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);//���SensorManager
	        //����ʱ��
	        mySensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
	        mySensorManager.connectSimulator();				//��Simulator����������
	    }
	    private SensorListener mySensorListener = new SensorListener(){
	    	 
	    	public void onAccuracyChanged(int sensor, int accuracy) {}	//��дonAccuracyChanged����
	    	 
	    	public void onSensorChanged(int sensor, float[] values) {		//��дonSensorChanged����
	    		if(sensor == SensorManager.SENSOR_ACCELEROMETER){//ֻ�����ٶȵı仯
	    			myTextView1.setText("x�����ϵļ��ٶ�Ϊ��"+values[0]);	//����ȡ��x������ʾ��TextView
	    			myTextView2.setText("y�����ϵļ��ٶ�Ϊ��"+values[1]);	//����ȡ��y������ʾ��TextView
	    			myTextView3.setText("z�����ϵļ��ٶ�Ϊ��"+values[2]);	//����ȡ��x������ʾ��TextView
	    		}
	    	}
	    };
	    @Override
	    protected void onResume() {//��д��onResume����
	    	mySensorManager.registerListener(//ע�����
	    			mySensorListener, //������SensorListener����
	    			SensorManager.SENSOR_ACCELEROMETER,//������������Ϊ���ٶ�
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