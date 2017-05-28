package com.contec.iecg.fragment;

import com.contec.iecg.activity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Right extends Fragment {

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.help_kuaijie, container, false);  
		
		return v;
	}

	//create view for other invoke
	 public   View createView(int layout_id){
		/*Fragment_Right.this.onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);*/
		 
		 return null;
	 };
}
