package com.contec.rmj.bluetooth.message.sxt;

public class SXTData {
	byte mYear,mMonth,mDay,mHour,mMinute;
	byte[] data = new byte[2];
	byte[] params;
	
	public SXTData(byte[] params) {
		this.params = params;
		mYear = params[0];
		mMonth = params[1];
		mDay = params[2];
		mHour = params[3];
		mMinute = params[4];
		data[0] = params[5];
		data[1] = params[6];
	}
	
	
	public byte getmYear() {
		return mYear;
	}
	public void setmYear(byte mYear) {
		this.mYear = mYear;
	}
	public byte getmMonth() {
		return mMonth;
	}
	public void setmMonth(byte mMonth) {
		this.mMonth = mMonth;
	}
	public byte getmDay() {
		return mDay;
	}
	public void setmDay(byte mDay) {
		this.mDay = mDay;
	}
	public byte getmHour() {
		return mHour;
	}
	public void setmHour(byte mHour) {
		this.mHour = mHour;
	}
	public byte getmMinute() {
		return mMinute;
	}
	public void setmMinute(byte mMinute) {
		this.mMinute = mMinute;
	}
	public double getData() {
		return (data[0]<<8 | data[1])/10.0;
	}
	public void setData(byte[] data) {
		this.data = data;
	}


	public byte[] getParams() {
		return params;
	}


	public void setParams(byte[] params) {
		this.params = params;
	}
	
	
	
	
}
