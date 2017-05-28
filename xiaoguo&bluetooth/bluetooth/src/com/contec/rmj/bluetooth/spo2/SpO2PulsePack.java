package com.contec.rmj.bluetooth.spo2;

import java.io.OutputStream;


public class SpO2PulsePack {
	public byte _SpO2;
	public byte _Pulse;
	public boolean writeToFile(OutputStream out)
	{
		Util.writeChar(out, this._SpO2);
		Util.writeChar(out, this._Pulse);
		return true;
	}
}
