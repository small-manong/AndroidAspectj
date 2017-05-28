package wyf.jc;									//���������
import android.app.Activity;				//���������
import android.app.AlertDialog;				//���������
import android.app.Dialog;					//���������
import android.app.AlertDialog.Builder;		//���������
import android.content.DialogInterface;		//���������
import android.content.DialogInterface.OnClickListener;	//���������
import android.os.Bundle;						//���������
import android.view.View;						//���������
import android.widget.Button;					//���������						
import android.widget.EditText;					//���������
public class Sample_6_3 extends Activity {
	final int COMMON_DIALOG = 1;				//��ͨ�Ի���id
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);							//���õ�ǰ��Ļ
        Button btn = (Button)findViewById(R.id.Button01);		//���Button����
        btn.setOnClickListener(new View.OnClickListener(){		//ΪButton����OnClickListener������
			@Override
			public void onClick(View v) {						//��дonClick����
				showDialog(COMMON_DIALOG);						//��ʾ��ͨ�Ի���
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {					//��дonCreateDialog����
		Dialog dialog = null;									//����һ��Dialog�������ڷ���
		switch(id){		//��id�����ж�
		case COMMON_DIALOG:
			Builder b = new AlertDialog.Builder(this);
			b.setIcon(R.drawable.header);		//���öԻ����ͼ��
			b.setTitle(R.string.btn);			//���öԻ���ı���
			b.setMessage(R.string.dialog_msg);	//���öԻ������ʾ����
			b.setPositiveButton(				//��Ӱ�ť
				R.string.ok, 
				new OnClickListener() {			//Ϊ��ť��Ӽ�����
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText et = (EditText)findViewById(R.id.EditText01);
						et.setText(R.string.dialog_msg);//����EditText����
					}
				});
			dialog = b.create();			//����Dialog����
			break;
		default:
			break;
		}
		return dialog;						//��������Dialog�Ķ���
	}
    
}