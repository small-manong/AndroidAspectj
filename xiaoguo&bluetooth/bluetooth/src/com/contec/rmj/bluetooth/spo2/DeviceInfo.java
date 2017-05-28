package com.contec.rmj.bluetooth.spo2;

import java.io.OutputStream;

public class DeviceInfo {
	public static final int  e_strlen = 32;
	public long  m_PIType; //!< 0:OK 1:PI数据无效
	public long  m_saveDataType;//!< 存储数据类型
	public String m_deviceType="";//!< 设备类型
	public String m_deviceID=""; //!< 设备标识
	public String  m_deviceCompany="";//!< 设备厂家标识

	public DeviceInfo()
	{

	}
	public long SaveDataType()
	{
		return m_saveDataType;
	}//!< 读取设备存储数据类型

	public void SaveDataType( long N)//!< 写入设备存储数据类型
	{
		m_saveDataType = N;
	}
	public long PIType()
	{
		return m_PIType;
	}//!< 读取PI标识

	public void PIType(long N)		//!< 写入PI标识
	{
		m_PIType = N;
	}

	public String  DeviceType()
	{
		return m_deviceType;
	}	//!< 读取设备类型

	public void DeviceType(String  p)			//!< 写入设备类型
	{
		m_deviceType = p;
	}

	public String DeviceID()
	{
		return m_deviceID;
	}		//!< 读取设备标识

	public void DeviceID(String  p)		    //!< 写入设备标识
	{
		m_deviceID = p;
	}

	public String DeviceCompany(){
		return m_deviceCompany;
	}  //!< 读取厂家标识

	public void DeviceCompany(String p)		     //!< 写入厂家标识
	{
		m_deviceCompany = p;
	}
	public boolean writeToFile(OutputStream out)
	{

		Util.writeLong(out, m_PIType);
		Util.writeLong(out, this.m_saveDataType);
		Util.writeString(out, this.m_deviceType, 32);
		Util.writeString(out, this.m_deviceID, 32);
		Util.writeString(out, this.m_deviceCompany, 32);
		return true;
	}
}
