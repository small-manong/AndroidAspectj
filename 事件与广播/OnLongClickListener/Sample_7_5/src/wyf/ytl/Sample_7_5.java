package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;
public class Sample_7_5 extends Activity implements OnLongClickListener{
	Button button;//������ť������ 
    public void onCreate(Bundle savedInstanceState) {//��д��onCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) this.findViewById(R.id.button);//�õ���ť������
        button.setTextSize(20);
        button.setOnLongClickListener(this);//ע�����
    }
	@Override
	public boolean onLongClick(View v) {//ʵ�ֽӿ��еķ���
		if(v == button){//�����µ��ǰ�ťʱ
			Toast.makeText(
					this, 
					"��ʱ�䰴���˰�ť", 
					Toast.LENGTH_SHORT
					).show();//��ʾ��ʾ
		}
		return false;
	}
}