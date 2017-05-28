package com.pxr.mp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import com.pangxurui.R;
import com.pangxurui.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	/** Called when the activity is first created. */
	private Button loginButton;
	private Button cancelButton;
	private EditText username;
	private EditText password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		loginButton = (Button) findViewById(R.id.login);
		final Intent intent = new Intent();
		intent.setClass(Login.this, Main.class);
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StringBuilder urlStr = new StringBuilder();
				urlStr.append("http://10.0.2.2:8080/PoliceConnect/login?");
				urlStr.append("username=" + username.getText().toString() + "&password=" + password.getText().toString());
				Log.d("systemout", username.getText().toString());
				try {
					URL urllogin = new URL(urlStr.toString());
					HttpURLConnection connection = (HttpURLConnection) urllogin
							.openConnection();
					connection.setRequestMethod("GET");
					InputStreamReader inStream = new InputStreamReader(
							connection.getInputStream());
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
					String string=reader.readLine();
					Log.d("systemout", string);
					if (string.startsWith("1")) {
						startActivity(intent);
						
					} else {
						
						Toast.makeText(Login.this, "用户名或者密码错误",
								Toast.LENGTH_SHORT).show();
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
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
			
	}
}