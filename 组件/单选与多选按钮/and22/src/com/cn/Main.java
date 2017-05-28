package com.cn;

import android.app.Activity;			 
import android.os.Bundle;				 
import android.widget.CheckBox;			 
import android.widget.CompoundButton;	 
import android.widget.ImageView;	 
import android.widget.RadioButton;		 
import android.widget.CompoundButton.OnCheckedChangeListener;	 
 

public class Main extends Activity {
	 
	 
     
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    CheckBox cb=(CheckBox)this.findViewById(R.id.CheckBox01);  
        cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){//为CheckBox添加监听器及开关灯业务代码
			 
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				setBulbState(isChecked);	
			}
        });
        RadioButton rb=(RadioButton)findViewById(R.id.off);
        rb.setOnCheckedChangeListener(new OnCheckedChangeListener(){ //为RadioButton添加监听器及开关灯业务代码
			 
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				setBulbState(!isChecked);
			}
        });
	}
	  //方法：设置程序状态的
    public void setBulbState(boolean state){
    	//设置图片状态
    	ImageView iv=(ImageView)findViewById(R.id.ImageView01);
		iv.setImageResource((state)?R.drawable.bulb_on:R.drawable.bulb_off);
		CheckBox cb=(CheckBox)this.findViewById(R.id.CheckBox01);  
		cb.setText((state)?R.string.off:R.string.on);		
		cb.setChecked(state);							//设置复选框文字状态
		RadioButton rb=(RadioButton)findViewById(R.id.off);
		rb.setChecked(!state);
		rb=(RadioButton)findViewById(R.id.on);
		rb.setChecked(state);							//设置单选按钮状态
    }

}