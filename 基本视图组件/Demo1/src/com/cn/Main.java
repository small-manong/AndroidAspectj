package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
      
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b_start=(Button)this.findViewById(R.id.bstart);
        Button b_about=(Button)this.findViewById(R.id.babout);
        

        Button b_help=(Button)this.findViewById(R.id.bhelp);
        b_help.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 setContentView(R.layout.bhelp);
				 Button b_return=(Button)Main.this.findViewById(R.id.breturn);
				 b_return.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						setContentView(R.layout.main);
						
					}
					 
				 });   
			}
        	
        });
        
        
        
        
        
        Button b_exit=(Button)this.findViewById(R.id.bexit);
        b_exit.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
			   System.exit(0);
				
			}
        	
        });
        
    }
}