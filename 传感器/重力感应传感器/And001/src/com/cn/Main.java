package com.cn;   

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;     
import android.widget.TextView;
public class Main extends Activity    {
	MainView mv;//��View 
	int k = 45;//������
	private SensorManagerSimulator mySensorManager; //SensorManager��������
	//private SensorManager mySensorManager;//SensorManager��������
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//���õ�ǰ���û�����
        mv = (MainView) findViewById(R.id.mainView);//��ȡ��View
        //mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	//���SensorManager
        //����ʱ��
        mySensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
        mySensorManager.connectSimulator();        
    } 
    private SensorListener mySensorListener = new SensorListener(){//������ 
    	 
    	public void onAccuracyChanged(int sensor, int accuracy) {}	//��дonAccuracyChanged����
    	 
    	public void onSensorChanged(int sensor, float[] values) {		//��дonSensorChanged����
    		if(sensor == SensorManager.SENSOR_ORIENTATION){//ֻ�����̬�ı仯
    			double pitch = values[SensorManager.DATA_Y];//�õ�pitch
    			double roll = values[SensorManager.DATA_Z];//�õ�roll
    			int x = 0;//��ʱ����,���м�ˮ������ʱ���õ�
    			int y = 0;
    			if(Math.abs(roll)<=k){//����x
    				x = mv.big_X + (int)(((mv.big.getWidth()-mv.small.getWidth())/2.0)
    						-(((mv.big.getWidth()-mv.small.getWidth())/2.0)*roll)/k);
    			}
    			else if(roll>k){
    				x = mv.big_X;
    			}
    			else{
    				x = mv.big_X + mv.big.getWidth() - mv.small.getWidth();
    			}
    			if(Math.abs(pitch)<=k){//����y 
    				y = mv.big_Y + (int)(((mv.big.getHeight()-mv.small.getHeight())/2.0) 
    						+(((mv.big.getHeight()-mv.small.getHeight())/2.0)*pitch)/k);
    			}
    			else if(pitch>k){
    				y = mv.big_Y + mv.big.getHeight() - mv.small.getHeight();
    			}
    			else{
    				y = mv.big_Y;
    			}
    			if(isContain(x, y)){//�м��ˮ��,�����Բ�ڲŸı�����
	     			mv.small_X = x;
	    			mv.small_Y = y;   				
    			}
    			mv.postInvalidate();//�ػ�MainView    			
    		}
    	}
    	public boolean isContain(int x, int y){//�жϵ��Ƿ���԰��
    		int tempx = (int) (x + mv.small.getWidth()/2.0);//�õ�ˮ��tempx����
    		int tempy = (int) (y + mv.small.getWidth()/2.0);//�õ�ˮ��tempy����
    		int ox = (int) (mv.big_X + mv.big.getWidth()/2.0);//�õ���Բ��x����
    		int oy = (int) (mv.big_X + mv.big.getWidth()/2.0);//�õ���Բ��y����
    		if(Math.sqrt((tempx-ox)*(tempx-ox)+(tempy-oy)*(tempy-oy))
    				>(mv.big.getWidth()/2.0-mv.small.getWidth()/2.0)){
    			return false;//����Բ��
    		}
    		else {
    			return true;//��Բ��
    		}
    	}    	
    };    
    @Override
    protected void onResume() {//��д��onResume����
    	mySensorManager.registerListener(//ע�����
    			mySensorListener, //������SensorListener����
    			SensorManager.SENSOR_ORIENTATION,//������������Ϊ��̬
    			SensorManager.SENSOR_DELAY_UI//Ƶ��
    			);
    	super.onResume();
    }	
    @Override
    protected void onPause() {//��дonPause����
    	mySensorManager.unregisterListener(mySensorListener);//ȡ��ע�������
    	super.onPause();
    }    
}