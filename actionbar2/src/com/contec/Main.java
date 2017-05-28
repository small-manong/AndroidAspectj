package com.contec;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActionBar actionBar = this.getActionBar();
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        TabContentFragment artistsFragment = new TabContentFragment();
        AlbumsFragment albumsFragment = new AlbumsFragment();
        actionBar.addTab(actionBar.newTab().setText("fragmenttabcontent").setTabListener(new MyTabListener(artistsFragment)));
        actionBar.addTab(actionBar.newTab().setText("fragmentalbums").setTabListener(new MyTabListener(albumsFragment)));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 当Action Bar的图标被单击时执行下面的Intent
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private class MyTabListener implements ActionBar.TabListener {
    	private  Fragment mFragment;
    	public MyTabListener(Fragment fragment) {
    		mFragment = fragment;
    		}
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			Log.d(" select", " select");
		}
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			Log.d("unselect", "unselect");
		}
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			Log.d("TabReselect", "TabReselect");
		}
		

    	}
}