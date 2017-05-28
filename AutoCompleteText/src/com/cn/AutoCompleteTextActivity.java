package com.cn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextActivity extends Activity {

	AutoCompleteTextView autotext;
	String textPrompt[]=new String[] {"abc","bcd","cde","def"};
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, textPrompt);
        adapter.setNotifyOnChange(true);
        autotext=(AutoCompleteTextView) findViewById(R.id.autotext);
        autotext.setAdapter(adapter);
        autotext.setThreshold(1);
    }
}