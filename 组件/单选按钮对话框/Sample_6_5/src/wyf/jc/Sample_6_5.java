package wyf.jc;					//���������
import android.app.Activity;	//���������
import android.app.AlertDialog;	//���������
import android.app.Dialog;		//���������
import android.app.AlertDialog.Builder;	//���������
import android.content.DialogInterface;	//���������
import android.os.Bundle;	//���������
import android.view.View;	//���������
import android.widget.Button;	//���������
import android.widget.EditText;	//���������
public class Sample_6_5 extends Activity {
	final int LIST_DIALOG_SINGLE = 3;		//��¼��ѡ�б�Ի����id
    @Override
    public void onCreate(Bundle savedInstanceState) {		//��дonCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);						//���õ�ǰ��Ļ
        Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new View.OnClickListener() {	//ΪButton����OnClickListener������
			@Override
			public void onClick(View v) {
				showDialog(LIST_DIALOG_SINGLE);			//��ʾ��ѡ��ť�Ի���
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {			//��дonCreateDialog����
		Dialog dialog = null;					//����һ��Dialog�������ڷ���
		switch(id){			//��id�����ж�
		case LIST_DIALOG_SINGLE:
			Builder b = new AlertDialog.Builder(this);	//����Builder����
			b.setIcon(R.drawable.header);				//����ͼ��	
			b.setTitle(R.string.title);					//���ñ���
			b.setSingleChoiceItems(						//���õ�ѡ�б�ѡ��
					R.array.msa, 
					0, 
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							EditText et = (EditText)findViewById(R.id.EditText01);
							et.setText("��ѡ���ˣ�"
									+ getResources().getStringArray(R.array.msa)[which]);
						}
					});
			b.setPositiveButton(						//���һ����ť
			R.string.ok,								//��ť��ʾ���ı�
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which){}
			});
			dialog = b.create();						//����Dialog����
			break;
		default:
			break;
		}
		return dialog;									//�������ɵ�Dialog����
	}
    
}