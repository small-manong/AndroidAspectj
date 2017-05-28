package wyf.ytl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Sample_7_6 extends Activity implements OnFocusChangeListener{
	TextView myTextView;
	ImageButton[] imageButtons = new ImageButton[4];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myTextView = (TextView) this.findViewById(R.id.myTextView);//�õ�myTextView������
        imageButtons[0] = (ImageButton) this.findViewById(R.id.button01);//�õ�button01������
        imageButtons[1] = (ImageButton) this.findViewById(R.id.button02);//�õ�button02������
        imageButtons[2] = (ImageButton) this.findViewById(R.id.button03);//�õ�button03������
        imageButtons[3] = (ImageButton) this.findViewById(R.id.button04);//�õ�button04������
        for(ImageButton imageButton : imageButtons){
        	imageButton.setOnFocusChangeListener(this);//��Ӽ���
        }
    }
	@Override
	public void onFocusChange(View v, boolean hasFocus) {//ʵ���˽ӿ��еķ���
		if(v.getId() == R.id.button01){//�ı����button01ʱ
			myTextView.setText("��ѡ������");
		}else if(v.getId() == R.id.button02){//�ı����button02ʱ
			myTextView.setText("��ѡ������");
		}else if(v.getId() == R.id.button03){//�ı����button03ʱ
			myTextView.setText("��ѡ����ţ��");
		}else if(v.getId() == R.id.button04){//�ı����button04ʱ
			myTextView.setText("��ѡ������");
		}else{//�������
			myTextView.setText("");
		}
	}
}