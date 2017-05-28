package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ImageButton;
import android.widget.TextView;
public class Sample_7_7 extends Activity implements OnKeyListener,OnClickListener{
	ImageButton[] imageButtons = new ImageButton[4];//������ť����
	TextView myTextView;//����TextView������
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        myTextView = (TextView) this.findViewById(R.id.myTextView);//�õ�myTextView������
        imageButtons[0] = (ImageButton) this.findViewById(R.id.button01);//�õ�button01������
        imageButtons[1] = (ImageButton) this.findViewById(R.id.button02);//�õ�button02������
        imageButtons[2] = (ImageButton) this.findViewById(R.id.button03);//�õ�button03������
        imageButtons[3] = (ImageButton) this.findViewById(R.id.button04);//�õ�button04������
        for(ImageButton imageButton : imageButtons){
        	imageButton.setOnClickListener(this);//��ӵ�������
        	imageButton.setOnKeyListener(this);//��Ӽ��̼���
        }
    }
	@Override
	public void onClick(View v) {//ʵ���˽ӿ��еķ���
		if(v.getId() == R.id.button01){//�ı����button01ʱ
			myTextView.setText("������˰�ťA��");
		}else if(v.getId() == R.id.button02){//�ı����button02ʱ
			myTextView.setText("������˰�ťB��");
		}else if(v.getId() == R.id.button03){//�ı����button03ʱ
			myTextView.setText("������˰�ťC��");
		}else if(v.getId() == R.id.button04){//�ı����button04ʱ
			myTextView.setText("������˰�ťD��");
		}else{//�������
			myTextView.setText("");
		}
	}    
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {//���̼���
		switch(keyCode){//�жϼ�����
		case 29://����A
			imageButtons[0].performClick();//ģ�ⵥ��
			imageButtons[0].requestFocus();//����ʹ֮��ý���
			break;
		case 30://����B
			imageButtons[1].performClick();//ģ�ⵥ��
			imageButtons[1].requestFocus();//����ʹ֮��ý���
			break;
		case 31://����C
			imageButtons[2].performClick();//ģ�ⵥ��
			imageButtons[2].requestFocus();//����ʹ֮��ý���
			break;
		case 32://����D
			imageButtons[3].performClick();//ģ�ⵥ��
			imageButtons[3].requestFocus();//����ʹ֮��ý���
			break;
		}
		return false;
	}
}