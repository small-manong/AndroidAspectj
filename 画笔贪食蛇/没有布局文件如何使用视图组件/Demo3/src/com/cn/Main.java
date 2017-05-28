package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout l=new LinearLayout(this);
      
        TextView t=new TextView(this);
           t.setText("asdfasdfads");
        Button b=new Button(this);
         b.setText("Œ“");
         b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(Main.this, "asdfa", 1).show();
				
			}
		});
        l.addView(t);
        l.addView(b);
        setContentView(l);
    }
}