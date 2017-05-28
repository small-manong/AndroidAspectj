package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
public class Sample_5_8 extends Activity {
	int[] imageIDs={
			R.drawable.bbta,R.drawable.bbtb,R.drawable.bbtc, 
			R.drawable.bbtd,R.drawable.bbte,R.drawable.bbtf,
			R.drawable.bbtg
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Gallery gl=(Gallery)this.findViewById(R.id.Gallery01);
        BaseAdapter ba=new BaseAdapter(){
			@Override
			public int getCount() {
				return imageIDs.length;
			}
			@Override
			public Object getItem(int arg0) {			    
				return null;
			}
			@Override
			public long getItemId(int arg0) {
				return 0;
			}
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				ImageView iv = new ImageView(Sample_5_8.this);
				iv.setImageResource(imageIDs[arg0]);
				iv.setScaleType(ImageView.ScaleType.FIT_XY);
				iv.setLayoutParams(new Gallery.LayoutParams(188,250));
				return iv;
			}        	
        };
        gl.setAdapter(ba);
        gl.setOnItemClickListener(
        		new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Gallery gl=(Gallery)findViewById(R.id.Gallery01);
						gl.setSelection(arg2);
					}        			
        		}
        );
    }
}