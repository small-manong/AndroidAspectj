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
		// 获取程序中的WebView组件
		show = (WebView) findViewById(R.id.show);
		StringBuilder sb = new StringBuilder();
		// 拼接一段HTML代码
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> 欢迎您 </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> 欢迎您访问<a href=\"http://www.qq.com\">"
			+ "QQ</a></h2>");
		sb.append("</body>");
		sb.append("</html>");
		// 使用简单的loadData方法会导致乱码，可能是Android API的Bug
		//show.loadData(sb.toString() , "text/html" , "utf-8");
		// 加载、并显示HTML代码
		show.loadDataWithBaseURL(null, sb.toString()
			, "text/html" , "utf-8", null);
	}
}