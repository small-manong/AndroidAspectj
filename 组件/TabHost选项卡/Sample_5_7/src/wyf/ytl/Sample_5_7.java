package wyf.ytl;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
public class Sample_5_7 extends TabActivity {
	private TabHost myTabhost;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTabhost=this.getTabHost();//从TabActivity上面获取放置Tab的TabHost
        LayoutInflater.from(this).inflate(R.layout.main, myTabhost.getTabContentView(), true);
        myTabhost.addTab(
        		myTabhost.newTabSpec("选项卡1")
        		.setIndicator("选项卡1", getResources().getDrawable(R.drawable.png1))
        		.setContent(R.id.linearLayout01)
        		); 
        myTabhost.addTab(
        		myTabhost.newTabSpec("选项卡2")
        		.setIndicator("选项卡2", getResources().getDrawable(R.drawable.png2))
        		.setContent(R.id.linearLayout02)
        		);
        myTabhost.addTab(
        		myTabhost.newTabSpec("选项卡3")
        		.setIndicator("选项卡3", getResources().getDrawable(R.drawable.png3))
        		.setContent(R.id.linearLayout03)
        		);        
    }
}