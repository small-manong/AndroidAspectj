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
            	//�жϺ����ַ����Ƿ�Ϸ�
            	 if(PhoneNumberUtils.isGlobalPhoneNumber(strTel)){
            		 Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel://"+strTel));
            		 Main.this.startActivity(i);
            	 }else{
            		 Toast.makeText(Main.this, "��ʽ����", 100).show();
            	 }
				
			}
	    	
	    });
	}

}