package com.contec.rmj.bluetooth.message.sp10w;

import java.io.OutputStream;

public class Struct_data {
	int dwData;
	int nID;
	public int getDwData() {
		return dwData;
	}
	public void setDwData(int dwData) {
		this.dwData = dwData;
	}
	public int getnID() {
		return nID;
	}
	public void setnID(int nID) {
		this.nID = nID;
	}
	public void writeToFile(OutputStream out){
		SaveHelper.saveInt(out, this.getDwData());
		SaveHelper.saveInt(out, this.getnID());
	}
}
