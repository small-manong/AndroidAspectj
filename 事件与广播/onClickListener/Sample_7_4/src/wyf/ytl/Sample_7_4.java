package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class Sample_7_4 extends Activity implements OnClickListener{
	Button[] buttons =  new Button[4];
	TextView textView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttons[0] = (Button) this.findViewById(R.id.button01);
        buttons[1] = (Button) this.findViewById(R.id.button02);
        buttons[2] = (Button) this.findViewById(R.id.button03);
        buttons[3] = (Button) this.findViewById(R.id.button04);
        textView = (TextView) this.findViewById(R.id.textView01);
        textView.setTextSize(18);
        for(Button button : buttons){
        	button.setOnClickListener(this);
        }
    }
	@Override
	public void onClick(View v) {//ʵ���¼���������
		if(v == buttons[0]){//���µ�һ����ťʱ
			textView.setText("��������"+((Button)v).getText()+"����ʱ�Ƿֿ�����ģ�");
		}
		else{//����������ťʱ
			textView.setText("��������" + ((Button)v).getText());
		}
	}
}