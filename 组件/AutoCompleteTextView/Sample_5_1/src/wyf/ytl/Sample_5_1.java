package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
public class Sample_5_1 extends Activity {
	private static final String[] myStr = new String[]{//常量数组
		"aaa", "bbb", "ccc", "aab", "aac", "aad"
	};
    public void onCreate(Bundle savedInstanceState) {//重写的onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//设置当前显示的用户界面
        ArrayAdapter<String> aa = new ArrayAdapter<String> (//创建适配器
        		this, //Context
        		android.R.layout.simple_dropdown_item_1line,//布局 
        		myStr);//资源数组
        AutoCompleteTextView myAutoCompleteTextView = //得到控件的引用
        	(AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView);
        myAutoCompleteTextView.setAdapter(aa);//设置适配器
        myAutoCompleteTextView.setThreshold(1);//定义需要用户输入的字符数
    }
}