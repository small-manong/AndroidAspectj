package com.contec.iecg.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Adapter_Style extends ArrayAdapter<String> {
	private Context mContext;
	private String[] mItems; 
	
	public Adapter_Style(Context context, int textViewResourceId,
			String[] objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mItems = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
		
		
	}

}
