package wyf.jc;							//���������
import android.app.Activity;			//���������
import android.app.AlertDialog;				//���������
import android.app.Dialog;				//���������
import android.app.AlertDialog.Builder;		//���������
import android.content.DialogInterface;		//���������
import android.os.Bundle;			//���������
import android.view.View;			//���������
import android.widget.Button;		//���������
import android.widget.EditText;	//���������
public class Sample_6_6 extends Activity {
	final int LIST_DIALOG_MULTIPLE = 4;				//��¼��ѡ��ť�Ի����id
	boolean[] mulFlags=new boolean[]{true,false,true};//��ʼ��ѡ���
	String[] items=null;//ѡ������
    @Override
    public void onCreate(Bundle savedInstanceState) {	//��дonCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);					//���õ�ǰ��Ļ
        items=getResources().getStringArray(R.array.msa);	//���XML�ļ��е��ַ�������
        Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new View.OnClickListener() {	//Ϊ��ť��Ӽ�����
			@Override
			public void onClick(View v) {
				showDialog(LIST_DIALOG_MULTIPLE);			//��ʾ��ѡ��ť�Ի���
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {				//��дonCreateDialog����
		Dialog dialog = null;
		switch(id){		//��id�����ж�
		case LIST_DIALOG_MULTIPLE:
			Builder b = new AlertDialog.Builder(this);		//����Builder����
			b.setIcon(R.drawable.header);					//����ͼ��
			b.setTitle(R.string.title);						//���ñ���
			b.setMultiChoiceItems(							//���ö�ѡѡ��
					R.array.msa,
					mulFlags,								//�����ʼ��ѡ��״̬
					new DialogInterface.OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
							mulFlags[which] = isChecked;		//����ѡ�б�־λ
							String result = "��ѡ���ˣ�";
							for(int i=0;i<mulFlags.length;i++){
								if(mulFlags[i]){		//�����ѡ�ѡ��
									result = result+items[i]+"��";
								}
							}
							EditText et = (EditText)findViewById(R.id.EditText01);
							et.setText(result.substring(0,result.length()-1));//����EditText��ʾ������
						}
					});
			b.setPositiveButton(					//��Ӱ�ť
					R.string.ok,
					new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {}
			});
			dialog = b.create();				//����Dialog����
			break;
		default:
			break;
		}
		return dialog;							//����Dialog����
	}
}