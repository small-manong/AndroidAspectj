package wyf.jc;							//声明包语句
import android.app.Activity;			//引入相关类
import android.app.AlertDialog;				//引入相关类
import android.app.Dialog;				//引入相关类
import android.app.AlertDialog.Builder;		//引入相关类
import android.content.DialogInterface;		//引入相关类
import android.os.Bundle;			//引入相关类
import android.view.View;			//引入相关类
import android.widget.Button;		//引入相关类
import android.widget.EditText;	//引入相关类
public class Sample_6_6 extends Activity {
	final int LIST_DIALOG_MULTIPLE = 4;				//记录多选按钮对话框的id
	boolean[] mulFlags=new boolean[]{true,false,true};//初始复选情况
	String[] items=null;//选项数组
    @Override
    public void onCreate(Bundle savedInstanceState) {	//重写onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);					//设置当前屏幕
        items=getResources().getStringArray(R.array.msa);	//获得XML文件中的字符串数组
        Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new View.OnClickListener() {	//为按钮添加监听器
			@Override
			public void onClick(View v) {
				showDialog(LIST_DIALOG_MULTIPLE);			//显示多选按钮对话框
			}
		});
    }
	@Override
	protected Dialog onCreateDialog(int id) {				//重写onCreateDialog方法
		Dialog dialog = null;
		switch(id){		//对id进行判断
		case LIST_DIALOG_MULTIPLE:
			Builder b = new AlertDialog.Builder(this);		//创建Builder对象
			b.setIcon(R.drawable.header);					//设置图标
			b.setTitle(R.string.title);						//设置标题
			b.setMultiChoiceItems(							//设置多选选项
					R.array.msa,
					mulFlags,								//传入初始的选中状态
					new DialogInterface.OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
							mulFlags[which] = isChecked;		//设置选中标志位
							String result = "您选择了：";
							for(int i=0;i<mulFlags.length;i++){
								if(mulFlags[i]){		//如果该选项被选中
									result = result+items[i]+"、";
								}
							}
							EditText et = (EditText)findViewById(R.id.EditText01);
							et.setText(result.substring(0,result.length()-1));//设置EditText显示的内容
						}
					});
			b.setPositiveButton(					//添加按钮
					R.string.ok,
					new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {}
			});
			dialog = b.create();				//生成Dialog方法
			break;
		default:
			break;
		}
		return dialog;							//返回Dialog方法
	}
}