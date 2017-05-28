package com.contec.rmj.bluetooth.message.sp10w;

import java.io.OutputStream;

public class CaseInfo {

	private byte mCaseID, mAge, mHeight, mWeight, mEthnic, mGender, mDrug,
			mSmoke,mEffective;
	private byte[] mScaler= new byte[2];
	private String mTime,mCaseName,mUserName;
	private byte mStandard;

	public void initCase(byte[] pack) {
		setmCaseID(pack[2]);
		setmAge(pack[3]);
		setmHeight(pack[4]);
		setmWeight(pack[5]);
		setmScaler(pack[6],pack[7]);
		setmEthnic((byte) (pack[8] & 0x3f));
		setmGender((byte) (((pack[8]&0xff) >> 4)==0?1:0));
		this.mStandard=(byte) (pack[8]&0xf);
	}

	public byte getmCaseID() {
		return mCaseID;
	}

	public void setmCaseID(byte mCaseID) {
		this.mCaseID = mCaseID;
		this.mCaseName = mCaseID+1+".dt";
		this.mUserName = "";
	}

	public byte getmAge() {
		return mAge;
	}

	public void setmAge(byte mAge) {
		this.mAge = mAge;
	}

	public byte getmHeight() {
		return mHeight;
	}

	public void setmHeight(byte mHeight) {
		this.mHeight = mHeight;
	}

	public byte getmWeight() {
		return mWeight;
	}

	public void setmWeight(byte mWeight) {
		this.mWeight = mWeight;
	}

	public byte getmEthnic() {
		return mEthnic;
	}

	public void setmEthnic(byte mEthnic) {
		this.mEthnic = mEthnic;
	}

	public byte getmGender() {
		return mGender;
	}

	public void setmGender(byte mGender) {
		this.mGender = mGender;
	}

	public byte[] getmScaler() {
		return mScaler;
	}

	public void setmScaler(byte packlow,byte packhigh) {
		this.mScaler[0] = packlow;
		this.mScaler[1] = packhigh;
	}

	public byte getmDrug() {
		return mDrug;
	}

	public void setmDrug(byte mDrug) {
		this.mDrug = mDrug;
	}

	public byte getmSmoke() {
		return mSmoke;
	}

	public void setmSmoke(byte mSmoke) {
		this.mSmoke = mSmoke;
	}

	public String getmTime() {
		return mTime;
	}
	

	public byte getmEffective() {
		return mEffective;
	}

	public void setmEffective(byte mEffective) {
		this.mEffective = mEffective;
	}

	public void setmTime(byte[] pack) {
		int year = (pack[3] & 0xff) | ((pack[4] & 0xff) << 8);
		int month = (byte) (pack[5] & 0xff);
		int day = (pack[6] & 0xff);
		int hour = (byte) (pack[7] & 0xff);
		int minute = (byte) (pack[8] & 0xff);
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(year);
		strBuffer.append("-");
		if (month < 10) {
			strBuffer.append("0");
			strBuffer.append(month);
		} else {
			strBuffer.append(month);
		}
		strBuffer.append("-");
		if (day < 10) {
			strBuffer.append("0");
			strBuffer.append(day);
		} else {
			strBuffer.append(day);
		}
		strBuffer.append(" ");
		if (hour < 10) {
			strBuffer.append("0");
			strBuffer.append(hour);
		} else {
			strBuffer.append(hour);
		}
		strBuffer.append(":");
		if (minute < 10) {
			strBuffer.append("0");
			strBuffer.append(minute);
		} else {
			strBuffer.append(minute);
		}
		String strDate = strBuffer.toString();
		this.mTime = strDate;
	}
	
	public void write(OutputStream out, CaseInfo info) {
		SaveHelper.saveInt(out, info.mEffective);
		SaveHelper.saveInt(out, info.mCaseID);
		SaveHelper.saveString(out, mUserName, 20);
		SaveHelper.saveInt(out, info.mGender);
		SaveHelper.saveInt(out, info.mEthnic);
		SaveHelper.saveInt(out, mStandard);
		SaveHelper.saveInt(out, mAge);
		SaveHelper.saveInt(out, info.mHeight);
		SaveHelper.saveInt(out, info.mWeight);
		SaveHelper.saveString(out, info.mTime, 20);
		SaveHelper.saveInt(out, info.mScaler[0] | (info.mScaler[1]<<8));
		SaveHelper.saveString(out, info.mCaseName, 100);
		SaveHelper.saveInt(out, info.mDrug);
		SaveHelper.saveInt(out, info.mSmoke);
	}

}
