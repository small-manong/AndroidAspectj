package wyf.jc;						//���������
import android.app.Activity;		//���������
import android.app.AlertDialog;		//���������
import android.app.Dialog;			//���������
import android.app.AlertDialog.Builder;		//������	����
import android.content.DialogInterface;	//���������
import android.os.Bundle;					//���������
import android.view.View;		//���������
import android.widget.Button;	//���������
import android.widget.EditText;	//���������
public class Sample_6_4 extends Activity {
	final int LIST_DIALOG = 2;			//�����б�Ի����id
    @Override
    public void onCreate(Bundle savedInstanceState) {		//��дonCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);						//���õ�ǰ��Ļ
        Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new View.OnClickListener() {	//Ϊ��ť���OnClickListener������
			@Override
			public void onClick(View v) {
				showDialog(LIST_DIALOG);			//��ʾ�б�Ի���
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {		//��д��onCreateDialog����
		Dialog dialog = null;
		switch(id){		//��id�����ж�
		case LIST_DIALOG:
			Builder b = new AlertDialog.Builder(this);		//����Builder����
			b.setIcon(R.drawable.header);					//����ͼ��
			b.setTitle(R.string.title);						//���ñ���
			b.setItems(										//�����б��еĸ�������
					R.array.msa, 							//�ַ�������
					new DialogInterface.OnClickListener() {	//Ϊ�б�����OnClickListener������
						@Override
						public void onClick(DialogInterface dialog, int which) {
							EditText et = (EditText)findViewById(R.id.EditText01);
							et.setText("��ѡ���ˣ�"
									+getResources().getStringArray(R.array.msa)[which]);
						}
					});
			dialog=b.create();							//����Dialog����
			break;
		default:
			break;			
		}
		return dialog;									//����Dialog����
	}
    
}