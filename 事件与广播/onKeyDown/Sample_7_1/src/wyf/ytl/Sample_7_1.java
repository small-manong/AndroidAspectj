package wyf.ytl;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
public class Sample_7_1 extends Activity {
	public final String TAG = "Sample_7_1";//�ַ�����
	MyButton myButton;//�Զ����Button
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myButton = new MyButton(this);
        myButton.setText("ȫ����ť");
        myButton.setTextSize(30);
        setContentView(myButton);
    }
	public boolean onKeyDown(int keyCode, KeyEvent event) {//��д�ļ��̰��¼���
		Log.d(TAG, "activity onKeyDown");//��ӡ��־
		return super.onKeyDown(keyCode, event);
	}
    class MyButton extends Button{//�Լ������Button
		public MyButton(Context context) {//������
			super(context);
		}
		public boolean onKeyDown(int keyCode, KeyEvent event){//��д�ļ��̰��¼���
			Log.d(TAG, "MyView onKeyDown");//��ӡ��־
			return false;
		}
    }
}