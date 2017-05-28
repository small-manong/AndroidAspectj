package wyf.jc;					//声明包语句
import android.app.Activity;	//引入相关类
import android.app.AlertDialog;	//引入相关类
import android.app.Dialog;		//引入相关类
import android.app.AlertDialog.Builder;	//引入相关类
import android.content.DialogInterface;	//引入相关类
import android.os.Bundle;	//引入相关类
import android.view.View;	//引入相关类
import android.widget.Button;	//引入相关类
import android.widget.EditText;	//引入相关类
public class Sample_6_5 extends Activity {
	final int LIST_DIALOG_SINGLE = 3;		//记录单选列表对话框的id
    @Override
    public void onCreate(Bundle savedInstanceState) {		//重写onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);						//设置当前屏幕
        Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new View.OnClickListener() {	//为Button设置OnClickListener监听器
			@Override
			public void onClick(View v) {
				showDialog(LIST_DIALOG_SINGLE);			//显示单选按钮对话框
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {			//重写onCreateDialog方法
		Dialog dialog = null;					//声明一个Dialog对象用于返回
		switch(id){			//对id进行判断
		case LIST_DIALOG_SINGLE:
			Builder b = new AlertDialog.Builder(this);	//创建Builder对象
			b.setIcon(R.drawable.header);				//设置图标	
			b.setTitle(R.string.title);					//设置标题
			b.setSingleChoiceItems(						//设置单选列表选项
					R.array.msa, 
					0, 
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							EditText et = (EditText)findViewById(R.id.EditText01);
							et.setText("您选择了："
									+ getResources().getStringArray(R.array.msa)[which]);
						}
					});
			b.setPositiveButton(						//添加一个按钮
			R.string.ok,								//按钮显示的文本
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which){}
			});
			dialog = b.create();						//生成Dialog对象
			break;
		default:
			break;
		}
		return dialog;									//返回生成的Dialog对象
	}
    
}