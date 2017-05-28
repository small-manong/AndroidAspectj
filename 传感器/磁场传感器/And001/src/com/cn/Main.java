package com.cn;   

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;
import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
public class Main extends Activity    {
	   TextView myTextView1;//x����Ĵų�����
	    TextView myTextView2;//y����Ĵų�����
	    TextView myTextView3;//z����Ĵų�����
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
	    		if(sensor == SensorManager.SENSOR_MAGNETIC_FIELD){//ֻ���ų��ı仯
	    			myTextView1.setText("x����Ĵų�����Ϊ��"+values[0]);	//��������ʾ��TextView
	    			myTextView2.setText("y����Ĵų�����Ϊ��"+values[1]);	//��������ʾ��TextView
	    			myTextView3.setText("z����Ĵų�����Ϊ��"+values[2]);	//��������ʾ��TextView
	    		}
	    	}
	    };
	 
	    protected void onResume() {//��д��onResume����
	    	mySensorManager.registerListener(//ע�����
	    			mySensorListener, //������SensorListener����
	    			SensorManager.SENSOR_MAGNETIC_FIELD,//������������Ϊ���ٶ�
	    			SensorManager.SENSOR_DELAY_UI//�������¼����ݵ�Ƶ��
	    			);
	    	super.onResume();
	    }	
	    
	    protected void onPause() {//��дonPause����
	    	mySensorManager.unregisterListener(mySensorListener);//ȡ��ע�������
	    	super.onPause();
	    }
}