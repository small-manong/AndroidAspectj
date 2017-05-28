package com.contec.iecg.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.contec.iecg.activity.R;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Fragment_Left extends ListFragment {
	public static String[] array = { "text1,", "text2", "text3", "text4",
			"text5,", "text6", "text7", "text8" };
	private Integer[] imgeIDs = {
			R.drawable.drawable_main_commonly_used_instructions,
			R.drawable.drawable_settings_gather, R.drawable.drawable_help_demo,
			R.drawable.drawable_help_insstructions,
			R.drawable.drawable_help_common_fault,
			R.drawable.drawable_help_demonstrate,
			R.drawable.drawable_help_vendor };
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub

		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		/*
		 * setListAdapter(new ArrayAdapter<String>(getActivity(),
		 * R.layout.helplist, this.getResources()
		 * .getStringArray(R.array.helptitle)));
		 */

		for (int x = 0; x < this.imgeIDs.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // 定义Map集合，保存每一行数据
			map.put("imgeId", String.valueOf(imgeIDs[x]));
			map.put("titlehelp", array[x]); // 与data_list.xml中的TextView组加匹配
			this.list.add(map); // 保存了所有的数据行
		}

		this.simpleAdapter = new SimpleAdapter(this.getActivity(), this.list,
				R.layout.helplist, new String[] { "imgeId", "titlehelp" } // Map中的key的名称
				, new int[] { R.id.imagehelp, R.id.titlehelp });
		/*
		 * this.simpleAdapter = new SimpleAdapter(this, this.list,
		 * R.layout.helplist, new String[] {"imgeId","titlehelp" } //
		 * Map中的key的名称 , new int[] { R.id.imagehelp, R.id.titlehelp }); //
		 * 是data_list.xml中定义的组件的资源ID setListAdapter(this.simpleAdapter);
		 */
		setListAdapter(simpleAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Log.d("你点击了第几个按钮", ""+position);
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			//Fragment_Right.this.startActivity(Activity_Main.class.get));
			break;

		default:
			break;
		}
		
	}

}
