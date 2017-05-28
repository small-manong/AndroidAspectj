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
	ImageButton start;//���š���ͣ��ť
	ImageButton stop;//ֹͣ��ť
	ActivityReceiver activityReceiver;
	int status = 1;//��ǰ��״̬,1û���������� ,2 ���ڲ�������,3��ͣ
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {//��д��onCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//���õ�ǰ���û�����
        start = (ImageButton) this.findViewById(R.id.start);//�õ�start������
        stop = (ImageButton) this.findViewById(R.id.stop);//�õ�stop��ť������
        start.setOnClickListener(this);//Ϊ��ť��Ӽ���
        stop.setOnClickListener(this);//Ϊ��ť��Ӽ���
        activityReceiver = new ActivityReceiver();//����BroadcastReceiver
        IntentFilter filter = new IntentFilter();//����IntentFilter������
        filter.addAction("wyf.ytl.update");//���Action
        registerReceiver(activityReceiver, filter);//ע�����
        Intent intent = new Intent(this, MyService.class);//����Intent
        startService(intent);//������̨Service
    }
    public class ActivityReceiver extends BroadcastReceiver{//�Զ����BroadcastReceiver
		@Override
		public void onReceive(Context context, Intent intent) {//��д��onReceive����
			// TODO Auto-generated method stub
			int update = intent.getIntExtra("update", -1);//�õ�intent�е�����
			switch(update){//��֧�ж�
			case 1://û����������
				status = 1; //���õ�ǰ״̬
				break;
			case 2://���ڲ�������
				start.setImageResource(R.drawable.png3);//����ͼƬ
				status = 2; //���õ�ǰ״̬
				break;
			case 3://��ͣ��
				start.setImageResource(R.drawable.png2);//����ͼƬ
				status = 3; //���õ�ǰ״̬
				break;
			}
		}
    }
	@Override
	public void onClick(View v) {//�ӿ��еķ���
		// TODO Auto-generated method stub
		Intent intent = new Intent("wyf.ytl.control");//����Intent
		switch(v.getId()){//��֧�ж�
		case R.id.start://���²��š���ͣ��ť
			intent.putExtra("ACTION", 1);//�������
			sendBroadcast(intent);//���͹㲥
			break;
		case R.id.stop://����ֹͣ��ť
			intent.putExtra("ACTION", 2);//�������
			sendBroadcast(intent);//���͹㲥
			break;
		}
	}
	@Override
	protected void onDestroy() {//�ͷ�ʱ������
		// TODO Auto-generated method stub
		super.onDestroy();
        Intent intent = new Intent(this, MyService.class);//����Intent
        stopService(intent);//ֹͣ��̨��Service
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){//�����˵� 
		menu.add(0,Menu.FIRST,0,"�˳�")
			.setIcon(android.R.drawable.ic_menu_delete);//����ͼ��
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){//ѡ��Ĳ˵���
		switch(item.getItemId()){//��֧�ж�
		case Menu.FIRST:
			showDialog(1);//��ʾ�Ի���
			break;
		}
		//�������ڴ˽�����չ
		return false;
	}
	@Override
	protected Dialog onCreateDialog(int id){//�����Ի���
		switch(id){//�ж�
		case 1:
			return new AlertDialog.Builder(this)
				.setTitle("��ȷ���˳���")
				.setPositiveButton("ȷ��", new android.content.DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						System.exit(0);//ֱ���˳�
					}
				})
				.setNegativeButton("ȡ��", null)//ȡ����ť
				.create();
		default:
			return null;
		}
	}
}