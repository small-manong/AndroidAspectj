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
        cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){//ΪCheckBox��Ӽ����������ص�ҵ�����
			 
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				setBulbState(isChecked);	
			}
        });
        RadioButton rb=(RadioButton)findViewById(R.id.off);
        rb.setOnCheckedChangeListener(new OnCheckedChangeListener(){ //ΪRadioButton��Ӽ����������ص�ҵ�����
			 
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				setBulbState(!isChecked);
			}
        });
	}
	  //���������ó���״̬��
    public void setBulbState(boolean state){
    	//����ͼƬ״̬
    	ImageView iv=(ImageView)findViewById(R.id.ImageView01);
		iv.setImageResource((state)?R.drawable.bulb_on:R.drawable.bulb_off);
		CheckBox cb=(CheckBox)this.findViewById(R.id.CheckBox01);  
		cb.setText((state)?R.string.off:R.string.on);		
		cb.setChecked(state);							//���ø�ѡ������״̬
		RadioButton rb=(RadioButton)findViewById(R.id.off);
		rb.setChecked(!state);
		rb=(RadioButton)findViewById(R.id.on);
		rb.setChecked(state);							//���õ�ѡ��ť״̬
    }

}