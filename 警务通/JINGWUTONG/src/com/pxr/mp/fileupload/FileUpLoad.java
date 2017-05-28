package com.pxr.mp.fileupload;

import com.pangxurui.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class FileUpLoad extends Activity {
	private ListView fileType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.fileupload);
		fileType=(ListView) findViewById(R.id.typelist);
		
		fileType.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					setContentView(R.layout.picupload);
					Button selectPic=(Button) findViewById(R.id.selectPic);
					selectPic.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							setContentView(R.layout.picpath);
							
						}
					});
					break;

				default:
					break;
				}
				
			}
		
		
		});
		
		
		
		
	}


	
}
