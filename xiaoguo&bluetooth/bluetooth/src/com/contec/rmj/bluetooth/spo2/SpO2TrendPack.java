package com.contec.rmj.bluetooth.spo2;

import java.io.OutputStream;
import java.io.RandomAccessFile;


public class SpO2TrendPack {
	int _PI;
	int _SpO2;
	int _Pulse;
	public boolean writeToFile(OutputStream out)
	{
		Util.writeShort(out, this._PI);
		Util.writeChar(out, this._SpO2);
		Util.writeChar(out, this._Pulse);
		return true;
	}
	public boolean writeToFile(RandomAccessFile file)
	{
		Util.writeShort(file, this._PI);
		Util.writeChar(file, this._SpO2);
		Util.writeChar(file, this._Pulse);
		return true;
	}
}
