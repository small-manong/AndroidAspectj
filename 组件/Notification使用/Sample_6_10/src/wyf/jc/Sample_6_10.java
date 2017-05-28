package wyf.jc;					//声明包语句
import android.app.Activity;		//引入相关类
import android.app.Notification;		//引入相关类
import android.app.NotificationManager;	//引入相关类
import android.app.PendingIntent;		//引入相关类
import android.content.Intent;			//引入相关类
import android.os.Bundle;				//引入相关类
import android.view.View;				//引入相关类
import android.widget.Button;			//引入相关类
public class Sample_6_10 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {//重写onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);				//设置当前屏幕
        Button btn = (Button)findViewById(R.id.btn);	//获取Button对象
        btn.setOnClickListener(new View.OnClickListener() {		//为按钮设置监听器
			@Override
			public void onClick(View v) {						//重写onClick方法
				Intent i = new Intent(Sample_6_10.this, NotifiedActivity.class);
				PendingIntent pi = PendingIntent.getActivity(Sample_6_10.this, 0, i, 0);
				Notification myNotification = new Notification();	//创建一个Notification对象
				myNotification.icon=R.drawable.header;				//Notification的图标
				myNotification.tickerText=getResources().getString(R.string.notification);			//
				myNotification.defaults=Notification.DEFAULT_SOUND;
				myNotification.setLatestEventInfo(Sample_6_10.this, "示例", "点击查看", pi);
				NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(0, myNotification);		//发送Notification
			}
		});
    }
}