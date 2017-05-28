package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
public class Sample_5_4 extends Activity {
	//������ԴͼƬ��andy��bill��edgar��torvalds��turing��id������
	int[] drawableIds=
	{R.drawable.andy,R.drawable.bill,R.drawable.edgar,R.drawable.torvalds,R.drawable.turing};
	//������Դ�ַ�����andy��bill��edgar��torvalds��turing��id������
	int[] msgIds={R.string.andy,R.string.bill,R.string.edgar,R.string.torvalds,R.string.turing};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView lv=(ListView)this.findViewById(R.id.ListView01);//��ʼ��ListView
        BaseAdapter ba=new BaseAdapter(){//ΪListView׼������������
			public int getCount() {return 5;}//�ܹ�5��ѡ��
			public Object getItem(int arg0) { return null; }
			public long getItemId(int arg0) { return 0; }
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				//��̬����ÿ���������Ӧ��View��ÿ��������View��LinearLayout
				//�а���һ��ImageView��һ��TextView����
				LinearLayout ll=new LinearLayout(Sample_5_4.this);//��ʼ��LinearLayout
				ll.setOrientation(LinearLayout.HORIZONTAL);		//���ó���	
				ll.setPadding(5,5,5,5);//������������
				ImageView  ii=new ImageView(Sample_5_4.this);//��ʼ��ImageView
				ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0]));//����ͼƬ
				ii.setScaleType(ImageView.ScaleType.FIT_XY);
				ii.setLayoutParams(new Gallery.LayoutParams(100,98));
				ll.addView(ii);//��ӵ�LinearLayout��
				TextView tv=new TextView(Sample_5_4.this);//��ʼ��TextView
				tv.setText(getResources().getText(msgIds[arg0]));//��������
				tv.setTextSize(24);//���������С
				tv.setTextColor(Sample_5_4.this.getResources().getColor(R.color.white));//����������ɫ
				tv.setPadding(5,5,5,5);//������������
			    tv.setGravity(Gravity.LEFT);
				ll.addView(tv);//��ӵ�LinearLayout��				
				return ll;
			}        	
        };
        lv.setAdapter(ba);//ΪListView��������������
        lv.setOnItemSelectedListener(//����ѡ��ѡ�еļ�����
           new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//��дѡ�ѡ���¼��Ĵ�����
				TextView tv=(TextView)findViewById(R.id.TextView01);//��ȡ������TextView
				LinearLayout ll=(LinearLayout)arg1;//��ȡ��ǰѡ��ѡ���Ӧ��LinearLayout
				TextView tvn=(TextView)ll.getChildAt(1);//��ȡ���е�TextView 
				StringBuilder sb=new StringBuilder();//��StringBuilder��̬������Ϣ
				sb.append(getResources().getText(R.string.ys));
				sb.append(":");
				sb.append(tvn.getText());
				String stemp=sb.toString();				
				tv.setText(stemp.split("\\n")[0]);//��Ϣ���ý�������TextView		
			}
			public void onNothingSelected(AdapterView<?> arg0){}
    	   }
        );   
        lv.setOnItemClickListener(//����ѡ������ļ�����
           new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//��дѡ������¼��Ĵ�����
				TextView tv=(TextView)findViewById(R.id.TextView01);//��ȡ������TextView
				LinearLayout ll=(LinearLayout)arg1;//��ȡ��ǰѡ��ѡ���Ӧ��LinearLayout
				TextView tvn=(TextView)ll.getChildAt(1);//��ȡ���е�TextView 
				StringBuilder sb=new StringBuilder();//��StringBuilder��̬������Ϣ
				sb.append(getResources().getText(R.string.ys));
				sb.append(":");
				sb.append(tvn.getText());
				String stemp=sb.toString();				
				tv.setText(stemp.split("\\n")[0]);//��Ϣ���ý�������TextView		
			}        	   
           }
        );
    } 
}