package com.cn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class ProximityAlertReciever extends BroadcastReceiver {

    
	public void onReceive(Context context, Intent intent) {
		// ��ȡ�Ƿ�Ϊ����ָ������
		boolean isEnter = intent.getBooleanExtra(
				LocationManager.KEY_PROXIMITY_ENTERING, false);
		if(isEnter)
		{
			// ��ʾ��ʾ��Ϣ
			Toast.makeText(context
				, "���Ѿ�������������"
				, Toast.LENGTH_LONG)
				.show();
		}
		else
		{
			// ��ʾ��ʾ��Ϣ
			Toast.makeText(context
				, "���Ѿ��뿪���������"
				, Toast.LENGTH_LONG)
				.show();		
		}
		
	}
	

}
