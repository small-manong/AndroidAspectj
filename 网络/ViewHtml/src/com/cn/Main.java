package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main extends Activity {
       
	WebView show;
	 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�����е�WebView���
		show = (WebView) findViewById(R.id.show);
		StringBuilder sb = new StringBuilder();
		// ƴ��һ��HTML����
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> ��ӭ�� </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> ��ӭ������<a href=\"http://www.qq.com\">"
			+ "QQ</a></h2>");
		sb.append("</body>");
		sb.append("</html>");
		// ʹ�ü򵥵�loadData�����ᵼ�����룬������Android API��Bug
		//show.loadData(sb.toString() , "text/html" , "utf-8");
		// ���ء�����ʾHTML����
		show.loadDataWithBaseURL(null, sb.toString()
			, "text/html" , "utf-8", null);
	}
}