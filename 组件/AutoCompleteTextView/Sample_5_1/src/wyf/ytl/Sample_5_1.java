package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
public class Sample_5_1 extends Activity {
	private static final String[] myStr = new String[]{//��������
		"aaa", "bbb", "ccc", "aab", "aac", "aad"
	};
    public void onCreate(Bundle savedInstanceState) {//��д��onCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//���õ�ǰ��ʾ���û�����
        ArrayAdapter<String> aa = new ArrayAdapter<String> (//����������
        		this, //Context
        		android.R.layout.simple_dropdown_item_1line,//���� 
        		myStr);//��Դ����
        AutoCompleteTextView myAutoCompleteTextView = //�õ��ؼ�������
        	(AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView);
        myAutoCompleteTextView.setAdapter(aa);//����������
        myAutoCompleteTextView.setThreshold(1);//������Ҫ�û�������ַ���
    }
}