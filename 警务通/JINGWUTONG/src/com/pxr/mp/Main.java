package com.pxr.mp;

import java.util.ArrayList;
import java.util.List;

import com.pangxurui.R;
import com.pxr.mp.fileupload.FileUpLoad;
import com.pxr.mp.infocollect.CarViolate;
import com.pxr.mp.infoquery.QueryEscaped_People;
import com.pxr.mp.location.GpsLocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {

	private ListView mainListView;
	private ListView infoQueryList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainListView = (ListView) findViewById(R.id.mainview);
		
		
		mainListView.setAdapter(new ArrayAdapter<String>(Main.this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		mainListView.setOnItemClickListener(new OnItemClickListener() {
  
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Toast.makeText(Main.this, Integer.toString((arg2+1)), Toast.LENGTH_SHORT).show();
				switch (arg2) {
				case 0:
					setContentView(R.layout.infoquery);
					infoQueryList=(ListView) findViewById(R.id.infoquery);
					infoQueryList.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							switch(arg2){
							case 0:  Intent intent = new Intent();
							intent.setClass(Main.this, QueryEscaped_People.class);
							startActivity(intent);
								break;
							
							}
							
						}
					});
					break;
				case 1:
					Intent intent = new Intent();
					intent.setClass(Main.this, CarViolate.class);
					startActivity(intent);
					break;
				case 2:
					Intent intent2 = new Intent();
					intent2.setClass(Main.this, FileUpLoad.class);
					startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent();
					intent3.setClass(Main.this, GpsLocation.class);
					startActivity(intent3);
					break;
				default:
					break;
				}

			
			}
			
			
		});
		
		
		

	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		data.add("信息查询");
		data.add("信息采集");
		data.add("文件上传");
		data.add("GPS定位功能");

		return data;
	}
}
