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
	int status = 1;//��ǰ��״̬,1û���������� ,2 ���ڲ�������,3��ͣ
	@Override
	public IBinder onBind(Intent intent) {//��д��onBind����
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {//��д��onCreate����
		// TODO Auto-generated method stub		
		status = 1;
		serviceReceiver = new ServiceReceiver();//����BroadcastReceiver
		IntentFilter filter = new IntentFilter();//����������
		filter.addAction("wyf.ytl.control");//���Action
		registerReceiver(serviceReceiver, filter);//ע��BroadcastReceiver
		super.onCreate();
	}
	@Override
	public void onDestroy() {//��д��onDestroy����
		// TODO Auto-generated method stub
		unregisterReceiver(serviceReceiver);//ȡ��ע��
		super.onDestroy();
	}
	public class ServiceReceiver extends BroadcastReceiver{//�Զ���BroadcastReceiver
		@Override
		public void onReceive(Context context, Intent intent) {//��д����Ӧ����
			// TODO Auto-generated method stub
			int action = intent.getIntExtra("ACTION", -1);//�ô���Ҫ������
			switch(action){
			case 1://���Ż���ͣ����
				if(status == 1){//��ǰû����������
					mp = MediaPlayer.create(context, R.raw.nx);
					status = 2;
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 2);
					sendBroadcast(sendIntent);
					mp.start();
				}
				else if(status == 2){//���ڲ�������
					mp.pause();	//ֹͣ
					status = 3;//�ı�״̬ 
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 3);//�������
					sendBroadcast(sendIntent);//���͹㲥
				}
				else if(status == 3){//��ͣ��
					mp.start();//��������
					status = 2;//�ı�״̬
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 2);//�������
					sendBroadcast(sendIntent);//���͹㲥
				}
				break;
			case 2://ֹͣ����
				if(status == 2 || status == 3){//�����л���ͣ��
					mp.stop();//ֹͣ����
					status = 1;//�ı�״̬
					Intent sendIntent = new Intent("wyf.ytl.update");
					sendIntent.putExtra("update", 1);//�������
					sendBroadcast(sendIntent);//���͹㲥
				}
			}
		}
	}
}