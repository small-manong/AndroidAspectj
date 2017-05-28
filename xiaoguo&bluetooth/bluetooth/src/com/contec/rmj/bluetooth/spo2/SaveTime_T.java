package com.contec.rmj.bluetooth.spo2;

import android.util.Log;

import java.io.OutputStream;

public class SaveTime_T {
	public long m_year;
	public long m_month;
	public long m_day;
	public long m_hour;
	public long m_minute;
	public long m_second;
    public long m_if_upload;


	public SaveTime_T()
	{
		this.m_year = 0;
		this.m_month = 0;
		this.m_day = 0;
		this.m_hour = 0;
		this.m_minute = 0;
		this.m_second = 0;
	}

	public long Year(){ return m_year; }		//!< 读取时间年

	public long Month()  { return m_month; }	    //!< 读取时间月

	public long Day()  { return m_day; }		    //!< 读取时间日

	public long Hour()  { return m_hour; }		//!< 读取时间时

	public long Minute()  { return m_minute; }	//!< 读取时间分

	public long Second()  { return m_second; }	//!< 读取时间秒

	public void SetTime(long y,  long mon,  long d,  long h,  long min,  long s)  //!< 写入时间
	{
		m_year = y;
		m_month = mon;
		m_day = d;
		m_hour = h;
		m_minute = min;
		m_second = s;
	}
	//! SetSaveDate (): 设置设备存储数据存储起始日期

	//! @param y : 年
	//! @param mon : 月
	//! @param d : 日
	//! @return void
	//! @author spz
	//! @date   2010-1-5
	//! @note   无
	void SetSaveDate( long y,  long mon,  long d)
	{
		m_year = y;
		m_month = mon;
		m_day = d;
	}
	//! SetSaveTime (): 设置设备存储数据存储起始时间

	//! @param h : 时
	//! @param min : 分
	//! @param s : 秒
	//! @return void
	//! @author spz
	//! @date   2010-1-5
	//! @note   无
	void SetSaveTime( long h,  long min,  long s)
	{
		m_hour = h;
		m_minute = min;
		m_second = s;
	}
	//保存数据是否上传过 1是上传过 0是未上传
	void saveIfUpload(long pIfUpload)
	{
	    m_if_upload = pIfUpload;
	}
	public  long getIfUpload()
	{
		return m_if_upload;
	}
	public boolean writeToFile(OutputStream out)
	{

		Log.i("-----------", "___________");
		Util.writeLong(out, this.m_year);
		Util.writeLong(out, this.m_month);
		Util.writeLong(out, this.m_day);
		Util.writeLong(out, this.m_hour);
		Util.writeLong(out, this.m_minute);
		Util.writeLong(out, this.m_second);
		return true;
	}
}
