package com.cn2;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.EditText;

public class Main extends Activity {
         
	EditText url;
	WebView show;
 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡҳ�����ı���WebView���
		url = (EditText) findViewById(R.id.url);
		show = (WebView) findViewById(R.id.show);
	}
 
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_SEARCH)
		{
			String urlStr = url.getText().toString();
			// ���ء�����ʾurlStr��Ӧ����ҳ
			show.loadUrl(urlStr);
			return true;
		}
		return false;
	}
}