package com.contec.rmj.bluetooth.spo2;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeDBAdapter extends SQLiteOpenHelper{

	SQLiteDatabase dbDatabase = getWritableDatabase();
	
	public TimeDBAdapter(Context context, String name, CursorFactory factory,
			int version) {
		super(context, Constants.DBNAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTable = "CREATE TABLE " + Constants.DBNAME + " ( "
		+ Constants.ID + " integer primary key autoincrement ,"
		+ Constants.TYPE
		+ " text , " + Constants.TIME_INFO + " text ) ";
		db.execSQL(createTable);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void insertTimeInfo(ContentValues cv) {
		String typeString = cv.getAsString(Constants.TYPE);
		String timeInfoString = cv.getAsString(Constants.TIME_INFO);
		dbDatabase.insert(Constants.DBNAME, null, cv);
	}
	
	public boolean isOnUpload(String saveTime) {
		boolean isOnUpload = false;
		int i = 0;
		String wherec = Constants.TIME_INFO+"='"+saveTime+"'";
		Cursor cursor =dbDatabase.query(Constants.DBNAME, null, wherec, null, null, null, null);
		i = cursor.getCount();
		cursor.close();
		if(i == 0)
			isOnUpload = false;
		else
			isOnUpload = true;
		return isOnUpload;
	}

}
