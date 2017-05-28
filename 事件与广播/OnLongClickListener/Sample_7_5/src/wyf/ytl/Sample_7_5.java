package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;
public class Sample_7_5 extends Activity implements OnLongClickListener{
	Button button;//声明按钮的引用 
    public void onCreate(Bundle savedInstanceState) {//重写的onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) this.findViewById(R.id.button);//得到按钮的引用
        button.setTextSize(20);
        button.setOnLongClickListener(this);//注册监听
    }
	@Override
	public boolean onLongClick(View v) {//实现接口中的方法
		if(v == button){//当按下的是按钮时
			Toast.makeText(
					this, 
					"长时间按下了按钮", 
					Toast.LENGTH_SHORT
					).show();//显示提示
		}
		return false;
	}
}