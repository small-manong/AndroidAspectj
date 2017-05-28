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
	//所有资源图片（andy、bill、edgar、torvalds、turing）id的数组
	int[] drawableIds=
	{R.drawable.andy,R.drawable.bill,R.drawable.edgar,R.drawable.torvalds,R.drawable.turing};
	//所有资源字符串（andy、bill、edgar、torvalds、turing）id的数组
	int[] msgIds={R.string.andy,R.string.bill,R.string.edgar,R.string.torvalds,R.string.turing};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView lv=(ListView)this.findViewById(R.id.ListView01);//初始化ListView
        BaseAdapter ba=new BaseAdapter(){//为ListView准备内容适配器
			public int getCount() {return 5;}//总共5个选项
			public Object getItem(int arg0) { return null; }
			public long getItemId(int arg0) { return 0; }
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				//动态生成每个下拉项对应的View，每个下拉项View由LinearLayout
				//中包含一个ImageView及一个TextView构成
				LinearLayout ll=new LinearLayout(Sample_5_4.this);//初始化LinearLayout
				ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
				ll.setPadding(5,5,5,5);//设置四周留白
				ImageView  ii=new ImageView(Sample_5_4.this);//初始化ImageView
				ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0]));//设置图片
				ii.setScaleType(ImageView.ScaleType.FIT_XY);
				ii.setLayoutParams(new Gallery.LayoutParams(100,98));
				ll.addView(ii);//添加到LinearLayout中
				TextView tv=new TextView(Sample_5_4.this);//初始化TextView
				tv.setText(getResources().getText(msgIds[arg0]));//设置内容
				tv.setTextSize(24);//设置字体大小
				tv.setTextColor(Sample_5_4.this.getResources().getColor(R.color.white));//设置字体颜色
				tv.setPadding(5,5,5,5);//设置四周留白
			    tv.setGravity(Gravity.LEFT);
				ll.addView(tv);//添加到LinearLayout中				
				return ll;
			}        	
        };
        lv.setAdapter(ba);//为ListView设置内容适配器
        lv.setOnItemSelectedListener(//设置选项选中的监听器
           new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//重写选项被选中事件的处理方法
				TextView tv=(TextView)findViewById(R.id.TextView01);//获取主界面TextView
				LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
				TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView 
				StringBuilder sb=new StringBuilder();//用StringBuilder动态生成信息
				sb.append(getResources().getText(R.string.ys));
				sb.append(":");
				sb.append(tvn.getText());
				String stemp=sb.toString();				
				tv.setText(stemp.split("\\n")[0]);//信息设置进主界面TextView		
			}
			public void onNothingSelected(AdapterView<?> arg0){}
    	   }
        );   
        lv.setOnItemClickListener(//设置选项被单击的监听器
           new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//重写选项被单击事件的处理方法
				TextView tv=(TextView)findViewById(R.id.TextView01);//获取主界面TextView
				LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
				TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView 
				StringBuilder sb=new StringBuilder();//用StringBuilder动态生成信息
				sb.append(getResources().getText(R.string.ys));
				sb.append(":");
				sb.append(tvn.getText());
				String stemp=sb.toString();				
				tv.setText(stemp.split("\\n")[0]);//信息设置进主界面TextView		
			}        	   
           }
        );
    } 
}