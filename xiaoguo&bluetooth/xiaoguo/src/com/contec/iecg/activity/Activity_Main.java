package com.contec.iecg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Activity_Main extends Activity {

	/* this is the top navigate imageview declare */
	ImageView mConfigure;
	ImageView mMedicalRecord;
	ImageView mHelp;
	ImageView mClient;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 不显示系统的标题栏
		setContentView(R.layout.help);

		mConfigure = (ImageView) findViewById(R.id.mConfigure);
		mMedicalRecord = (ImageView) findViewById(R.id.mMedicalRecord);
		mHelp = (ImageView) findViewById(R.id.mHelp);
		mClient = (ImageView) findViewById(R.id.mClient);

		mConfigure.setOnClickListener(new OnClickListener_ImageView());
		mClient.setOnClickListener(new OnClickListener_ImageView());
		mMedicalRecord.setOnClickListener(new OnClickListener_ImageView());
		mHelp.setOnClickListener(new OnClickListener_ImageView());
	}

	private class OnClickListener_ImageView implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Log.d("system", "点击图标");

			if (v == mConfigure) {
				mConfigure
						.setImageResource(R.drawable.drawable_main_settings_down);
				mMedicalRecord
						.setImageResource(R.drawable.drawable_main_history_up);
				mHelp.setImageResource(R.drawable.drawable_main_help_up);
				mClient.setImageResource(R.drawable.drawable_main_client_up);
				// mConfigure.setBackgroundResource(R.drawable.drawable_main_settings_down);
			} else if (v == mMedicalRecord) {
				mMedicalRecord
						.setImageResource(R.drawable.drawable_main_history_down);
				mConfigure
						.setImageResource(R.drawable.drawable_main_settings_up);
				mHelp.setImageResource(R.drawable.drawable_main_help_up);
				mClient.setImageResource(R.drawable.drawable_main_client_up);
			} else if (v == mHelp) {
				mHelp.setImageResource(R.drawable.drawable_main_help_down);
				mConfigure
						.setImageResource(R.drawable.drawable_main_settings_up);
				mClient.setImageResource(R.drawable.drawable_main_client_up);
				mMedicalRecord
						.setImageResource(R.drawable.drawable_main_history_up);
			} else if (v == mClient) {
				mClient.setImageResource(R.drawable.drawable_main_client_down);
				mHelp.setImageResource(R.drawable.drawable_main_help_up);
				mConfigure
						.setImageResource(R.drawable.drawable_main_settings_up);
				mMedicalRecord
						.setImageResource(R.drawable.drawable_main_history_up);
			}
		}

	}
}