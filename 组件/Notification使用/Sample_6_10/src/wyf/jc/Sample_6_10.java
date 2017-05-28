package wyf.jc;					//���������
import android.app.Activity;		//���������
import android.app.Notification;		//���������
import android.app.NotificationManager;	//���������
import android.app.PendingIntent;		//���������
import android.content.Intent;			//���������
import android.os.Bundle;				//���������
import android.view.View;				//���������
import android.widget.Button;			//���������
public class Sample_6_10 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {//��дonCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);				//���õ�ǰ��Ļ
        Button btn = (Button)findViewById(R.id.btn);	//��ȡButton����
        btn.setOnClickListener(new View.OnClickListener() {		//Ϊ��ť���ü�����
			@Override
			public void onClick(View v) {						//��дonClick����
				Intent i = new Intent(Sample_6_10.this, NotifiedActivity.class);
				PendingIntent pi = PendingIntent.getActivity(Sample_6_10.this, 0, i, 0);
				Notification myNotification = new Notification();	//����һ��Notification����
				myNotification.icon=R.drawable.header;				//Notification��ͼ��
				myNotification.tickerText=getResources().getString(R.string.notification);			//
				myNotification.defaults=Notification.DEFAULT_SOUND;
				myNotification.setLatestEventInfo(Sample_6_10.this, "ʾ��", "����鿴", pi);
				NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(0, myNotification);		//����Notification
			}
		});
    }
}