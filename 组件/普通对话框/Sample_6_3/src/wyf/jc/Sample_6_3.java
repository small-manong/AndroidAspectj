package wyf.jc;									//声明包语句
import android.app.Activity;				//引入相关类
import android.app.AlertDialog;				//引入相关类
import android.app.Dialog;					//引入相关类
import android.app.AlertDialog.Builder;		//引入相关类
import android.content.DialogInterface;		//引入相关类
import android.content.DialogInterface.OnClickListener;	//引入相关类
import android.os.Bundle;						//引入相关类
import android.view.View;						//引入相关类
import android.widget.Button;					//引入相关类						
import android.widget.EditText;					//引入相关类
public class Sample_6_3 extends Activity {
	final int COMMON_DIALOG = 1;				//普通对话框id
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);							//设置当前屏幕
        Button btn = (Button)findViewById(R.id.Button01);		//获得Button对象
        btn.setOnClickListener(new View.OnClickListener(){		//为Button设置OnClickListener监听器
			@Override
			public void onClick(View v) {						//重写onClick方法
				showDialog(COMMON_DIALOG);						//显示普通对话框
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {					//重写onCreateDialog方法
		Dialog dialog = null;									//声明一个Dialog对象用于返回
		switch(id){		//对id进行判断
		case COMMON_DIALOG:
			Builder b = new AlertDialog.Builder(this);
			b.setIcon(R.drawable.header);		//设置对话框的图标
			b.setTitle(R.string.btn);			//设置对话框的标题
			b.setMessage(R.string.dialog_msg);	//设置对话框的显示内容
			b.setPositiveButton(				//添加按钮
				R.string.ok, 
				new OnClickListener() {			//为按钮添加监听器
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText et = (EditText)findViewById(R.id.EditText01);
						et.setText(R.string.dialog_msg);//设置EditText内容
					}
				});
			dialog = b.create();			//生成Dialog对象
			break;
		default:
			break;
		}
		return dialog;						//返回生成Dialog的对象
	}
    
}