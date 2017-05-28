package com.cn;   

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;
import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
public class Main extends Activity    {
	   TextView myTextView1;//x方向的磁场分量
	    TextView myTextView2;//y方向的磁场分量
	    TextView myTextView3;//z方向的磁场分量
	    //SensorManager mySensorManager;//SensorManager对象引用
	    SensorManagerSimulator mySensorManager;//声明SensorManagerSimulator对象,调试时用
	    @Override 
	    public void onCreate(Bundle savedInstanceState) {//重写onCreate方法
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);//设置当前的用户界面
	        myTextView1 = (TextView) findViewById(R.id.myTextView1);//得到myTextView1的引用
	        myTextView2 = (TextView) findViewById(R.id.myTextView2);//得到myTextView2的引用
	        myTextView3 = (TextView) findViewById(R.id.myTextView3);//得到myTextView3的引用
	        //mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);//获得SensorManager
	        //调试时用
	        mySensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
	        mySensorManager.connectSimulator();				//与Simulator服务器连接
	    }
	    private SensorListener mySensorListener = new SensorListener(){
	     
	    	public void onAccuracyChanged(int sensor, int accuracy) {}	//重写onAccuracyChanged方法
	    	 
	    	public void onSensorChanged(int sensor, float[] values) {		//重写onSensorChanged方法
	    		if(sensor == SensorManager.SENSOR_MAGNETIC_FIELD){//只检查磁场的变化
	    			myTextView1.setText("x方向的磁场分量为："+values[0]);	//将数据显示到TextView
	    			myTextView2.setText("y方向的磁场分量为："+values[1]);	//将数据显示到TextView
	    			myTextView3.setText("z方向的磁场分量为："+values[2]);	//将数据显示到TextView
	    		}
	    	}
	    };
	 
	    protected void onResume() {//重写的onResume方法
	    	mySensorManager.registerListener(//注册监听
	    			mySensorListener, //监听器SensorListener对象
	    			SensorManager.SENSOR_MAGNETIC_FIELD,//传感器的类型为加速度
	    			SensorManager.SENSOR_DELAY_UI//传感器事件传递的频度
	    			);
	    	super.onResume();
	    }	
	    
	    protected void onPause() {//重写onPause方法
	    	mySensorManager.unregisterListener(mySensorListener);//取消注册监听器
	    	super.onPause();
	    }
}