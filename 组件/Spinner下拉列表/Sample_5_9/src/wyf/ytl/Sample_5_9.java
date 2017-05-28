package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
public class Sample_5_9 extends Activity {
	final static int WRAP_CONTENT=-2;//��ʾWRAP_CONTENT�ĳ���
	//������ԴͼƬ��������������id������
	int[] drawableIds={R.drawable.football,R.drawable.basketball,R.drawable.volleyball};
	//������Դ�ַ�����������������id������
	int[] msgIds={R.string.zq,R.string.lq,R.string.pq};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Spinner sp=(Spinner)this.findViewById(R.id.Spinner01);//��ʼ��Spinner
        BaseAdapter ba=new BaseAdapter(){//ΪSpinner׼������������
			@Override
			public int getCount() {return 3;}//�ܹ�����ѡ��
			@Override
			public Object getItem(int arg0) { return null; }
			@Override
			public long getItemId(int arg0) { return 0; }
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// ��̬����ÿ���������Ӧ��View��ÿ��������View��LinearLayout
				//�а���һ��ImageView��һ��TextView����
				//��ʼ��LinearLayout
				LinearLayout ll=new LinearLayout(Sample_5_9.this);
				ll.setOrientation(LinearLayout.HORIZONTAL);		//���ó���	
				//��ʼ��ImageView
				ImageView  ii=new ImageView(Sample_5_9.this);
				ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0]));//����ͼƬ
				ll.addView(ii);//��ӵ�LinearLayout��
				//��ʼ��TextView
				TextView tv=new TextView(Sample_5_9.this);
				tv.setText(" "+getResources().getText(msgIds[arg0]));//��������
				tv.setTextSize(24);//���������С
				tv.setTextColor(R.color.black);//����������ɫ
				ll.addView(tv);//��ӵ�LinearLayout��
				return ll;
			}        	
        };
        sp.setAdapter(ba);//ΪSpinner��������������
        sp.setOnItemSelectedListener(//����ѡ��ѡ�еļ�����
           new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//��дѡ�ѡ���¼��Ĵ�����
				TextView tv=(TextView)findViewById(R.id.TextView01);//��ȡ������TextView
				LinearLayout ll=(LinearLayout)arg1;//��ȡ��ǰѡ��ѡ���Ӧ��LinearLayout
				TextView tvn=(TextView)ll.getChildAt(1);//��ȡ���е�TextView 
				StringBuilder sb=new StringBuilder();//��StringBuilder��̬������Ϣ
				sb.append(getResources().getText(R.string.ys));
				sb.append(":");
				sb.append(tvn.getText());
				tv.setText(sb.toString());//��Ϣ���ý�������TextView		
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }        	   
           }
        );
    }
}