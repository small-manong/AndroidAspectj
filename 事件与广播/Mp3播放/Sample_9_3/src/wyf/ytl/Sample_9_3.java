package wyf.ytl;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
public class Sample_9_3 extends Activity implements OnClickListener{
	ImageButton start;//播放、暂停按钮
	ImageButton stop;//停止按钮
	ActivityReceiver activityReceiver;
	int status = 1;//当前的状态,1没有声音播放 ,2 正在播放声音,3暂停
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {//重写的onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//设置当前的用户界面
        start = (ImageButton) this.findViewById(R.id.start);//得到start的引用
        stop = (ImageButton) this.findViewById(R.id.stop);//得到stop按钮的引用
        start.setOnClickListener(this);//为按钮添加监听
        stop.setOnClickListener(this);//为按钮添加监听
        activityReceiver = new ActivityReceiver();//创建BroadcastReceiver
        IntentFilter filter = new IntentFilter();//创建IntentFilter过滤器
        filter.addAction("wyf.ytl.update");//添加Action
        registerReceiver(activityReceiver, filter);//注册监听
        Intent intent = new Intent(this, MyService.class);//创建Intent
        startService(intent);//启动后台Service
    }
    public class ActivityReceiver extends BroadcastReceiver{//自定义的BroadcastReceiver
		@Override
		public void onReceive(Context context, Intent intent) {//重写的onReceive方法
			// TODO Auto-generated method stub
			int update = intent.getIntExtra("update", -1);//得到intent中的数据
			switch(update){//分支判断
			case 1://没有声音播放
				status = 1; //设置当前状态
				break;
			case 2://正在播放声音
				start.setImageResource(R.drawable.png3);//更换图片
				status = 2; //设置当前状态
				break;
			case 3://暂停中
				start.setImageResource(R.drawable.png2);//更换图片
				status = 3; //设置当前状态
				break;
			}
		}
    }
	@Override
	public void onClick(View v) {//接口中的方法
		// TODO Auto-generated method stub
		Intent intent = new Intent("wyf.ytl.control");//创建Intent
		switch(v.getId()){//分支判断
		case R.id.start://按下播放、暂停按钮
			intent.putExtra("ACTION", 1);//存放数据
			sendBroadcast(intent);//发送广播
			break;
		case R.id.stop://按下停止按钮
			intent.putExtra("ACTION", 2);//存放数据
			sendBroadcast(intent);//发送广播
			break;
		}
	}
	@Override
	protected void onDestroy() {//释放时被调用
		// TODO Auto-generated method stub
		super.onDestroy();
        Intent intent = new Intent(this, MyService.class);//创建Intent
        stopService(intent);//停止后台的Service
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){//弹出菜单 
		menu.add(0,Menu.FIRST,0,"退出")
			.setIcon(android.R.drawable.ic_menu_delete);//设置图标
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){//选择的菜单项
		switch(item.getItemId()){//分支判断
		case Menu.FIRST:
			showDialog(1);//显示对话框
			break;
		}
		//将来可在此进行扩展
		return false;
	}
	@Override
	protected Dialog onCreateDialog(int id){//创建对话框
		switch(id){//判断
		case 1:
			return new AlertDialog.Builder(this)
				.setTitle("您确定退出？")
				.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						System.exit(0);//直接退出
					}
				})
				.setNegativeButton("取消", null)//取消按钮
				.create();
		default:
			return null;
		}
	}
}