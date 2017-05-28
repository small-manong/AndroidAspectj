package com.cn;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener, Runnable {
	private Handler handler;
	private TextView tvCount;
	private int count = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnStart = (Button) findViewById(R.id.btnStart);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		Button btnShowToast = (Button) findViewById(R.id.btnShowToast);
		tvCount = (TextView) findViewById(R.id.tvCount);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnShowToast.setOnClickListener(this);
		handler = new Handler();

	}

	public void run() {
		tvCount.setText("Count：" + String.valueOf(++count));
		handler.postDelayed(this, 1000);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnStart:
		   handler.postDelayed(this, 1000);		
			break;
		case R.id.btnStop:
			handler.removeCallbacks(this);
			break;
		case R.id.btnShowToast:
			handler.postAtTime(new RunToast(this){},
				android.os.SystemClock.uptimeMillis()+3*1000);
			break;
		}
	}
	class RunToast implements Runnable{
         private Context context;
         public RunToast(Context context){
        	 this.context=context;
         }
		public void run() {
			Toast.makeText(context, "3秒后显示Toast提示信息", 1).show(); 
			
		}
		
	}
	

}