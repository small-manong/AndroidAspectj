package wyf.ytl;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
public class MyService extends Service{
	MediaPlayer mp;
	ServiceReceiver serviceReceiver;
	int status = 1;//当前的状态,1没有声音播放 ,2 正在播放声音,3暂停
	@Override
	public IBinder onBind(Intent intent) {//重写的onBind方法
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {//重写的onCreate方法
		// TODO Auto-generated method stub		
		status = 1;
		serviceReceiver = new ServiceReceiver();//创建BroadcastReceiver
		IntentFilter filter = new IntentFilter();//创建过滤器
		filter.addAction("wyf.ytl.control");//添加Action
		registerReceiver(serviceReceiver, filter);//注册BroadcastReceiver
		super.onCreate();
	}
	@Override
	public void onDestroy() {//重写的onDestroy方法
		// TODO Auto-generated method stub
		unregisterReceiver(serviceReceiver);//取消注册
		super.onDestroy();
	}
	public class ServiceReceiver extends BroadcastReceiver{//自定义BroadcastReceiver
		@Override
		public void onReceive(Context context, Intent intent) {//重写的响应方法
			// TODO Auto-generated method stub
			int action = intent.getIntExtra("ACTION", -1);//得带需要的数据
			switch(action){
			case 1://播放或暂停声音
				if(status == 1){//当前没有声音播放
					mp = MediaPlayer.create(context, R.raw.nx);
					status = 2;
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 2);
					sendBroadcast(sendIntent);
					mp.start();
				}
				else if(status == 2){//正在播放声音
					mp.pause();	//停止
					status = 3;//改变状态 
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 3);//存放数据
					sendBroadcast(sendIntent);//发送广播
				}
				else if(status == 3){//暂停中
					mp.start();//播放声音
					status = 2;//改变状态
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 2);//存放数据
					sendBroadcast(sendIntent);//发送广播
				}
				break;
			case 2://停止声音
				if(status == 2 || status == 3){//播放中或暂停中
					mp.stop();//停止播放
					status = 1;//改变状态
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 1);//存放数据
					sendBroadcast(sendIntent);//发送广播
				}
			}
		}
	}
}