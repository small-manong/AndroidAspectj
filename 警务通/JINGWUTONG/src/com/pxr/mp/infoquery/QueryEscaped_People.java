package com.pxr.mp.infoquery;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.pangxurui.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QueryEscaped_People extends Activity {

	private Button queryEscaped;
	private Button queryPic;
	private EditText escapedIdno;
	private TextView resultInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.escaped_people_query);
		escapedIdno = (EditText) findViewById(R.id.idno);
		queryEscaped = (Button) findViewById(R.id.queryInfo);
		resultInfo = (TextView) findViewById(R.id.resultinfo);
		queryPic = (Button) findViewById(R.id.queryPic);
		queryEscaped.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String idno = escapedIdno.getText().toString();
				StringBuilder urlStr = new StringBuilder();
				urlStr.append("http://10.0.2.2:8080/PoliceConnect/escapedpeople?");
				Log.i("idno", idno);
				urlStr.append("idno=" + idno);
				try {
					URL urllogin = new URL(urlStr.toString());
					HttpURLConnection connection = (HttpURLConnection) urllogin
							.openConnection();
					connection.setRequestMethod("GET");
					InputStreamReader inStream = new InputStreamReader(
							connection.getInputStream());
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream(),
									"gb2312"));
					String string = reader.readLine();
					Log.d("systemout", string);
					if (true) {
						resultInfo.setText(string);
					} /*
					 * else { Toast.makeText(QueryEscaped_People.this, "Ã»ÓÐ·¸×ï¼ÇÂ¼",
					 * Toast.LENGTH_SHORT).show(); }
					 */

					inStream.close();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		queryPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String idno = escapedIdno.getText().toString();
				StringBuilder urlStr = new StringBuilder();
				urlStr.append("http://10.0.2.2:8080/PoliceConnect/escapedpic?");
				Log.i("idno", idno);
				urlStr.append("idno=" + idno);
				try {
					URL urlQuery = new URL(urlStr.toString());
					HttpURLConnection connection = (HttpURLConnection) urlQuery
							.openConnection();
					connection.setRequestMethod("GET");
					InputStreamReader inStream = new InputStreamReader(
							connection.getInputStream());
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream(),
									"gb2312"));
					String string = reader.readLine();
					Log.d("systemout", string);
					StringBuilder urlPicStr = new StringBuilder();
					urlPicStr.append("http://10.0.2.2:8080/PoliceConnect/"
							+ string);
					URL urlQueryPic = new URL(urlPicStr.toString());
					connection = (HttpURLConnection) urlQueryPic
							.openConnection();
					InputStream picStream = connection.getInputStream();
					ImageView pic=(ImageView) findViewById(R.id.peoplePic);
					BufferedInputStream bufin=new BufferedInputStream(picStream); 
					Bitmap picBitmap=BitmapFactory.decodeStream(bufin);
					pic.setImageBitmap(picBitmap);
					if (true) {

					}

					inStream.close();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}

}
