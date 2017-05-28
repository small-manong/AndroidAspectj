package com.cn;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends Activity {
	ListView providers;
	LocationManager lm;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	providers = (ListView) findViewById(R.id.providers);
    	// ��ȡϵͳ��LocationManager����
    	lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
    	// ��ȡϵͳ���е�LocationProvider������
    	List<String> providerNames=lm.getAllProviders();
    	ArrayAdapter<String>adapter=new ArrayAdapter<String>(
    		this,
    		android.R.layout.simple_list_item_1,
    		providerNames
    			);
    	// ʹ��ListView����ʾ���п��õ�LocationProvider
    	providers.setAdapter(adapter);
    }
}