package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ToggleButton;
 

public class Main extends Activity {
	 
	 
     
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	 					  
		 ToggleButton tb=(ToggleButton)this.findViewById(R.id.ToggleButton01);  
	        tb.setOnCheckedChangeListener(new OnCheckedChangeListener(){//ΪToggleButton��Ӽ�����
			 
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {							//��дonCheckedChanged����
					setBulbState(isChecked);			//���ÿؼ�״̬
				}
	        });
	    }    
	    //���������ó���״̬�ķ���
	    public void setBulbState(boolean state){//true on  false off
	    	//����ͼƬ״̬
	    	ImageView iv=(ImageView)findViewById(R.id.ImageView01);
			iv.setImageResource((state)?R.drawable.bulb_on:R.drawable.bulb_off);//����ͼƬ״̬
			//����ToggleButton״̬
			ToggleButton tb=(ToggleButton)this.findViewById(R.id.ToggleButton01);        
			tb.setChecked(state);//����ToggleButton״̬
		}

}