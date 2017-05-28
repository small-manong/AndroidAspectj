package com.contec.rmj.bluetooth.spo2;

import android.view.Menu;

public class Constants {
	public static boolean isWebOk=true;//根据该值判断某些情况下上传数据失败该如何更新界面，adapter里面用到了该值
	public static final int RESULT_FAIL = 0;
	public static final int RESULT_SUCC = 1;

	public static final String JOB_TYPE="JOB_TYPE";
	public static final int TYPE_ECG = 1;
	public static final int TYPE_NIBP = 2;
	public static final int TYPE_SPO2 = 3;

	public static final String TAG = "contec debug";
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	public static final String LANGUAGE_KEY = "LANGUAGE_KEY";
	public static final String CN = "CN";
	public static final String US = "US";
	public static final int RESGISTE_RETURN = 2;
	public static final String PACKAGENAME = "com.contec";

	public static final int WAIT_DOWNLOAD_PROGRESS = 0;
	public static final int BEGIN_DOWNLOAD = 1;
	public static final int UNFOUND = 110;
	public static final int WAITING_CONNTECTION = 130;
	public static final int CONNECTTING = 140 ;
	public static final int BLUETOOTH_CONNTECTION_FAILED = 150;
	public static final int BLUETOOTH_SANF_OVER = 180;
	public static final int DATA_FAILED_UPLOAD = 160;
	public static final int SEARCH = 200;
	public static final int CANCELED_UPLOAD = 170;

	/**
	 * bluetoothlist.xml sharepreferce
	 */
	public static final String LENGTH = "length";
	public static final String SAVETIME = "saveDateTime";
	public static final String CMS50IW ="CMS50IW";
	public static final String NAME = "name";
	public static final String MAC = "mac";
	public static final String DOUHAO =",";
	public static final String BLUE_LIST_SHARE = "bluetoothList";
	public static final String USERS = "users";

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	public static final int MESSAGE_SHOW_SEND_DLG = 6;
	public static final int UM_DONE_RECEIVE_SAVE_DATA = 7;
	public static final int UM_SENDSAVEDATA_PROGRESS = 8;
	public static final int MESSAGE_PROGRESS_DLG = 9;
	public static final int MESSAGE_SAVE_FILE_DLG = 10;
	public static final int MESSAGE_SCANFILE_OVER = 0;
	public final static String FIEST = "FIRST";
	public static final String DBNAME = "save_time";
	public static final String TYPE = "type";
	public static final String ID = "id";
	public static final String TIME_INFO = "time_info";
	public static TimeDBAdapter dbapter;

	public static int DATA_HAD_UPDATE = 1;
	public static int UPLOAD_DATA_TYPE = 0;//1：CMS50EW 2：CMS50IW
	

}
