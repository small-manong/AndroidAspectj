package wyf.ytl;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
public class Sample_7_1 extends Activity {
	public final String TAG = "Sample_7_1";//字符常量
	MyButton myButton;//自定义的Button
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myButton = new MyButton(this);
        myButton.setText("全屏按钮");
        myButton.setTextSize(30);
        setContentView(myButton);
    }
	public boolean onKeyDown(int keyCode, KeyEvent event) {//重写的键盘按下监听
		Log.d(TAG, "activity onKeyDown");//打印日志
		return super.onKeyDown(keyCode, event);
	}
    class MyButton extends Button{//自己定义的Button
		public MyButton(Context context) {//构造器
			super(context);
		}
		public boolean onKeyDown(int keyCode, KeyEvent event){//重写的键盘按下监听
			Log.d(TAG, "MyView onKeyDown");//打印日志
			return false;
		}
    }
}