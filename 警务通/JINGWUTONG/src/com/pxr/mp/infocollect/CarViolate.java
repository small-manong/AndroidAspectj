package com.pxr.mp.infocollect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.pangxurui.R;
import com.pxr.mp.Main;
import com.pxr.mp.infoquery.QueryEscaped_People;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CarViolate extends Activity {
	private ListView collcetListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infocolect);
		collcetListView = (ListView) findViewById(R.id.infocollect);
		collcetListView.setAdapter(new ArrayAdapter<String>(CarViolate.this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		collcetListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				switch (arg2) {
				case 0:
					setContentView(R.layout.carviolate);
					
					Button cancel = (Button) findViewById(R.id.cancel);
					  Button OK = (Button) findViewById(R.id.OK);
					OK.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							StringBuilder carVio = new StringBuilder();
							carVio.append("http://10.0.2.2:8080/PoliceConnect/insertVehicle?");
							  EditText name = (EditText) findViewById(R.id.name);
							  EditText idno = (EditText) findViewById(R.id.idno);
							  EditText license = (EditText) findViewById(R.id.license);
							 
							  Spinner faultRecord = (Spinner) findViewById(R.id.faultRecord);
							  faultRecord.setOnItemSelectedListener(new OnItemSelectedListener() {
								  public void onItemSelected(AdapterView<?> adapterView, View view, int position, 
										  long id) { 
									  
										  } 

										  @Override 
										  public void onNothingSelected(AdapterView<?> adapterView) { 
											  Log.d("system.out", "没有选中"); 
										  } 
							  });
							  faultRecord.setPrompt("选择违章类型");
//							  ArrayAdapter<CharSequence> adapterColor =new 
//							  adapterColor=ArrayAdapter.createFromResource(this, R.array.faulttype,  android.R.layout.simple_spinner_item);
							  EditText penally = (EditText) findViewById(R.id.penally);
							carVio.append("name="+name.getText().toString());
							carVio.append("&idno="+idno.getText().toString());
							carVio.append("&license="+license.getText().toString());
							String faultRecordtxt=null;
							switch (faultRecord.getBaseline()) {
							case 1:
								faultRecordtxt="闯红灯";
								break;
							case 2:
								faultRecordtxt="无证驾驶";
							case 3:
								faultRecordtxt="醉驾";
							case 4:
								faultRecordtxt="违规停车";
							default:
								break;
							}
							carVio.append("&faultRecord="+faultRecordtxt);
							carVio.append("&penally="+penally.getText().toString());
							try {
								URL carUrl=new URL(carVio.toString());
								HttpURLConnection carCon=(HttpURLConnection) carUrl.openConnection();
								carCon.setRequestMethod("GET");
								InputStreamReader inStream = new InputStreamReader(
										carCon.getInputStream());
								BufferedReader reader = new BufferedReader(
										new InputStreamReader(carCon.getInputStream(),
												"gb2312"));
								String string = reader.readLine();
								
								if(string.startsWith("1")){
									Toast.makeText(CarViolate.this, "添加成功", Toast.LENGTH_SHORT).show();
								}else {
									Toast.makeText(CarViolate.this, "添加失败，请您填写完整信息。", Toast.LENGTH_SHORT).show();
								}
								
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});

					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					break;
				}
			}
		});

	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		data.add("机动车违章信息采集");
		data.add("治安信息上报");
		data.add("社区警务信息上报");
		data.add("交通违法信息上报");
		data.add("现场取证笔录");
		data.add("现场违法事件处理");
		return data;
	}

}
