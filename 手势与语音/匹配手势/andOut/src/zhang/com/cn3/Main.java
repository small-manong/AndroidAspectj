package zhang.com.cn3;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends Activity {
	// �������Ʊ༭���
			GestureOverlayView gestureView;
			// ��¼�ֻ������е����ƿ�
			GestureLibrary gestureLibrary;
			@Override
			public void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.main);
				// ��ȡ��һ�����������������ƿ�
				gestureLibrary = GestureLibraries
					.fromFile("/sdcard/mygestures");
				if (gestureLibrary.load())
				{
					Toast.makeText(Main.this,"�����ļ�װ�سɹ���" ,
						8000).show();
				}
				else
				{
					Toast.makeText(Main.this,"�����ļ�װ��ʧ�ܣ�" ,
						8000).show();
				}	
				// ��ȡ���Ʊ༭���
				gestureView = (GestureOverlayView) findViewById(R.id.gesture);
				// Ϊ���Ʊ༭������¼�������
				gestureView.addOnGesturePerformedListener(
					new OnGesturePerformedListener()
					{
						@Override
						public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture)
						{
							// ʶ���û��ո������Ƶ�����
							ArrayList<Prediction> predictions = gestureLibrary
								.recognize(gesture);
							ArrayList<String> result = new ArrayList<String>();
							//���������ҵ���Prediction����
							for (Prediction pred : predictions)
							{
								// ֻ�����ƶȴ���2.0�����ƲŻᱻ���
								if (pred.score > 2.0)
								{
									result.add("�����ơ�" + pred.name + "�����ƶ�Ϊ"
										+ pred.score);
								}
							}
							if (result.size() > 0)
							{
								ArrayAdapter adapter = new ArrayAdapter(
										Main.this,
									android.R.layout.simple_dropdown_item_1line, result
										.toArray());
								// ʹ��һ����List�ĶԻ�������ʾ����ƥ�������
								new AlertDialog.Builder(Main.this)
									.setAdapter(adapter, null)
									.setPositiveButton("ȷ��", null).show();
							}
							else
							{
								Toast.makeText(Main.this,"�޷��ҵ���ƥ������ƣ�" ,
									8000).show();
							}
						}
					});
	}
}