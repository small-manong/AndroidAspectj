package com.contec.rmj.bluetooth.message.sp10w;

import java.io.OutputStream;

public class ParamInfo {
	private double mFvc;
	private double mfev1;
	private double mPef;
	private double mFev1Per;
	public double getmFev1Per() {
		return mFev1Per;
	}
	public void setmFev1Per(double mFev1Per) {
		this.mFev1Per = mFev1Per;
	}
	private double mFef25;
	private double mFef50;
	private double mFef75;
	private double mFef2575;
	private char mCaseLen;
	public double getmFvc() {
		return mFvc;
	}
	public void setmFvc(double mFvc) {
		this.mFvc = mFvc;
	}
	public double getMfev1() {
		return mfev1;
	}
	public void setMfev1(double mfev1) {
		this.mfev1 = mfev1;
	}
	public double getmPef() {
		return mPef;
	}
	public void setmPef(double mPef) {
		this.mPef = mPef;
	}
	public double getmFef25() {
		return mFef25;
	}
	public void setmFef25(double mFef25) {
		this.mFef25 = mFef25;
	}
	public double getmFef50() {
		return mFef50;
	}
	public void setmFef50(double mFef50) {
		this.mFef50 = mFef50;
	}
	public double getmFef75() {
		return mFef75;
	}
	public void setmFef75(double mFef75) {
		this.mFef75 = mFef75;
	}
	public double getmFef2575() {
		return mFef2575;
	}
	public void setmFef2575(double mFef2575) {
		this.mFef2575 = mFef2575;
	}
	public char getmCaseLen() {
		return mCaseLen;
	}
	public void setmCaseLen(char mCaseLen) {
		this.mCaseLen = mCaseLen;
	}
	public void write(OutputStream out,ParamInfo info){
		SaveHelper.saveDouble(out,info.mFvc);
		SaveHelper.saveDouble(out,info.mfev1);
		SaveHelper.saveDouble(out,info.mPef);
		SaveHelper.saveDouble(out, info.mFev1Per);
		SaveHelper.saveDouble(out,info.mFef25);
		SaveHelper.saveDouble(out,info.mFef50);
		SaveHelper.saveDouble(out,info.mFef75);
		SaveHelper.saveDouble(out,info.mFef2575);
		SaveHelper.saveChar(out, info.mCaseLen);
	}
}
