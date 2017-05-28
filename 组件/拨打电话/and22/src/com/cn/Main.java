package com.cn;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 

public class Main extends Activity {
	 

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    Button bCall=(Button)this.findViewById(R.id.Button01);
	    bCall.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 EditText eTel=(EditText)findViewById(R.id.myEditText);                	 
            	 String strTel=eTel.getText().toString();
            	//判断号码字符串是否合法
            	 if(PhoneNumberUtils.isGlobalPhoneNumber(strTel)){
            		 Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel://"+strTel));
            		 Main.this.startActivity(i);
            	 }else{
            		 Toast.makeText(Main.this, "格式不对", 100).show();
            	 }
				
			}
	    	
	    });
	}

}