package com.cn;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
 

public class Main extends Activity {
	           
     
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	      DatePicker dp = (DatePicker)findViewById(R.id.datepicker);
	        TimePicker tp = (TimePicker)findViewById(R.id.timepicker);
	        Calendar c = Calendar.getInstance();				//获得Calendar对象
	        int year = c.get(Calendar.YEAR);
	        int monthOfYear = c.get(Calendar.MONTH);
	        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
	        dp.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener(){	//初始化DatePicker
			 
				public void onDateChanged(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					flushDate(year,monthOfYear,dayOfMonth);	//更新EditText所显示内容
				}
	        });
	        tp.setOnTimeChangedListener(new OnTimeChangedListener(){		//为TimePicker添加监听器
			 
				public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
					flushTime(hourOfDay,minute);			//更新EditText所显示内容
				}
	        });
	 
	}
	//方法：刷新EditText所显示的内容
    public void flushDate(int year, int monthOfYear,int dayOfMonth){
    	EditText et = (EditText)findViewById(R.id.etDate);
    	et.setText("您选择的日期是："+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日。");
    }
    //方法：刷新时间EditText所显示的内容
    public void flushTime(int hourOfDay,int minute){
    	EditText et = (EditText)findViewById(R.id.etTime);
    	et.setText("您选择的时间是："+hourOfDay+"时"+minute+"分。");
	}    

}