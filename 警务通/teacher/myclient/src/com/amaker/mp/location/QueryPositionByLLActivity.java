package com.amaker.mp.location;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amaker.mp.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class QueryPositionByLLActivity extends MapActivity {
	private MapController mMapController01; 
	  private MapView mMapView01;
	  private Button mButton01,mButton02,mButton03; 
	  private EditText mEditText01;
	  private EditText mEditText02;
	  private int intZoomLevel=0;
	  /* Map����ʱ��Ĭ�����꣺�찲�� */
	  private double dLat=116.391177;
	  private double dLng=39.9067452;

	  @Override 
	  protected void onCreate(Bundle icicle) 
	  { 
	    super.onCreate(icicle); 
	    setContentView(R.layout.query_position_by_ll); 

	    /* ����MapView���� */ 
	    mMapView01 = (MapView)findViewById(R.id.myMapView1); 
	    mMapController01 = mMapView01.getController(); 
	    /* ����MapView����ʾѡ����ǡ��ֵ��� */
	    mMapView01.setSatellite(false); 
	    mMapView01.setStreetView(true);
	    /* Ĭ�ϷŴ�Ĳ㼶 */
	    intZoomLevel = 17; 
	    mMapController01.setZoom(intZoomLevel); 
	    /* ����Map���е�ΪĬ�Ͼ�γ�� */
	    refreshMapView();
	    
	    mEditText01 = (EditText)findViewById(R.id.myEdit1); 
	    mEditText02 = (EditText)findViewById(R.id.myEdit2);
	    
	    /* �ͳ���ѯ��Button */ 
	    mButton01 = (Button)findViewById(R.id.myButton1); 
	    mButton01.setOnClickListener(new Button.OnClickListener() 
	    { 
	     
	      public void onClick(View v) 
	      { 
	        /* ��γ�ȿհ׼�� */
	        if(mEditText01.getText().toString().equals("")||
	           mEditText02.getText().toString().equals(""))
	        {
	          showDialog("���Ȼ�γ����д����ȷ!");
	        }
	        else
	        {
	          /* ȡ������ľ�γ�� */
	          dLng=Double.parseDouble(mEditText01.getText().toString());
	          dLat=Double.parseDouble(mEditText02.getText().toString());
	          /* ������ľ�γ������Map */
	          refreshMapView(); 
	        }
	      } 
	    }); 
	     
	    /* �Ŵ�Map��Button */
	    mButton02 = (Button)findViewById(R.id.myButton2); 
	    mButton02.setOnClickListener(new Button.OnClickListener() 
	    { 
	   
	      public void onClick(View v) 
	      { 
	        intZoomLevel++; 
	        if(intZoomLevel>mMapView01.getMaxZoomLevel()) 
	        { 
	          intZoomLevel = mMapView01.getMaxZoomLevel(); 
	        } 
	        mMapController01.setZoom(intZoomLevel); 
	      } 
	    }); 
	     
	    /* ��СMap��Button */
	    mButton03 = (Button)findViewById(R.id.myButton3); 
	    mButton03.setOnClickListener(new Button.OnClickListener() 
	    { 
	     
	      public void onClick(View v) 
	      { 
	        intZoomLevel--; 
	        if(intZoomLevel<1) 
	        { 
	          intZoomLevel = 1; 
	        } 
	        mMapController01.setZoom(intZoomLevel); 
	      } 
	    });
	  } 
	  
	  /* ����Map��method */
	  public void refreshMapView() 
	  { 
	    GeoPoint p = new GeoPoint((int)(dLat* 1E6), (int)(dLng* 1E6)); 
	    mMapView01.displayZoomControls(true);
	    /* ��Map���е�����GeoPoint */
	    mMapController01.animateTo(p); 
	    mMapController01.setZoom(intZoomLevel); 
	  } 
	   
	  @Override 
	  protected boolean isRouteDisplayed() 
	  { 
	    return false; 
	  }
	  
	  /* ��ʾDialog��method */
	  private void showDialog(String mess){
	    new AlertDialog.Builder(QueryPositionByLLActivity.this).setTitle("Message")
	    .setMessage(mess)
	    .setNegativeButton("ȷ��", new DialogInterface.OnClickListener()
	    {
	      public void onClick(DialogInterface dialog, int which)
	      {
	      }
	    })
	    .show();
	  }
}
