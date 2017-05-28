package com.contec.rmj.bluetooth.spo2;

import android.util.Log;


public class ReadSpO2PackInfo {
	public ReadSpO2PackInfo()
	{
		this.m_version = new VERSION();

	}
	public static final int e_pack_anypack = 0x00;
	public static final int e_pack_realtime = 0x01;
	public static final int e_pack_devicetype = 0x02;
	public static final int e_pack_devicecomp = 0x03;
	public static final int e_pack_deviceid = 0x04;
	public static final int e_pack_username = 0x05;
	public static final int e_pack_version = 0x06;
	public static final int e_pack_savedate = 0x07;
	public static final int e_pack_datalen = 0x08;
	public static final int e_pack_savedatapi = 0x09;
	public static final int e_pack_datanum = 0x0A;
	public static final int e_pack_commerr = 0x0B;
	public static final int e_pack_freeback = 0x0C;
	public static final int e_pack_cutreas = 0x0D;
	public static final int e_pack_pitype = 0x0E;
	public static final int e_pack_savedata = 0x0F;
	public static final int e_pack_usernum = 0x10;
	public static final int e_pack_message = 0x11; //设备通知数据包
	public static final int e_pack_savetime = 0x12;
	public static final int e_pack_onlyIDF = 0x13;
	public static final int e_pack_onlyIDS = 0x14;
	public static final int e_pack_saveDataPIType = 0x15;

	public static final int e_pack_setdevID	= 0x04;
	public static final int e_pack_setcompany = 0x7B;
	public static final int e_pack_setdevtype = 0x7C;
	public static final int e_pack_command = 0x7D;

	public static final int e_pack_neverpack = 0xBD; //收不到的包，等待e_pack_commerr判断。

// 无效数据值
 public class noname
 {
	 public static final int e_spO2_exclude = 0x7f,
	 e_pulse_exclude = 0xff,
	 e_PI_exclude = 0xffff ;
 }



 public class AVAILDATE
 {
 	public int SearchTime;    //1：搜索时间太长，0：OK，
 	public int DeviceError;   //1：探头错误，0：OK
 	public int SearchPulse;   //1：探索脉搏，0：OK
 	public int PulseSound;	//1: 脉搏声音提示
 	public int bPI;			//1: 无PI 0：有PI
 	public int  Wave;			//波形
 	public int  Bar;			//棒图
 	public int  Pulse;	    //脉率
 	public int SpO2;;			//血氧饱和度
 	public int PI;	//灌注指数
 }

	//返回是否有存储数据
	public  boolean HaveSaveData() { return m_bHaveSaveData; }
	public  DeviceInfo DevInfo()  { return m_deviceinfo; }
	public boolean m_bHaveSaveData;

	//! m_savedataPI:有PI数据的存储数据包
//	SpO2TrendPack m_savedataPI;

	public static final int e_datasize = 3;
	public static final int e_wBufSize = 32;



	//返回用户姓名的用户索引
	public int UserNameIndex()
	{
		return m_userNameIndex;
	}

	//返回用户姓名
	public String UserName()
	{
		return m_userName;
	}


	//返回存储数据长度的用户索引
	public  int UserLengthIndex()
	{
		return m_userLengthIndex;
	}

	//返回存储数据长度的数据段号
	public int DataLengthIndex()
	{
		return m_dataLengthIndex;
	}


	//返回存储时间用户索引
	public int UserTimeIndex()
	{
		return m_userTimeIndex;
	}

	//返回存储时间数据段号
	public int DataTimeIndex()
	{
		return m_dataTimeIndex;
	}

	//返回存储数据起始时间
	public SaveTime_T SaveTime()
	{
		return m_saveTime;
	}


	//返回数据长度
	public  int GetSaveDataLength() {
		return m_saveDataLength;
	}
	SpO2TrendPack m_savedataPI = new SpO2TrendPack();
	//! 无PI数据的趋势数据包

	class SpO2PulsePack
	{
		byte _SpO2;
		byte _Pulse;
	}
	//! m_savedata:无PI数据的存储数据包
	SpO2PulsePack[] m_savedata = new SpO2PulsePack[e_datasize];

	//! m_deviceinfo:设备信息
	DeviceInfo m_deviceinfo = new DeviceInfo();

	//! m_saveTime:时间信息
	SaveTime_T m_saveTime = new SaveTime_T();
	//! m_userTimeIndex:存储时间的用户索引
	public  int m_userTimeIndex;
	//! m_dataTimeIndex:存储时间的数据段号
	public  int m_dataTimeIndex;

	//! m_commandError:命令错误
	public 	int m_commandError;

	//! m_command:错误命令包类型
	public  int m_command;
	//! m_freeBack:空闲反馈，初始值为0，收到此命令为1
	public int m_freeBack;

	//! m_cutReason:断开通知原因码
	public  int m_cutReason;

	//! m_saveDataLength:数据长度
	public int m_saveDataLength;

	//! m_saveDataPIType:存储数据P类型
	public int m_saveDataPIType;

	//! m_userLengthIndex:存储数据长度的用户索引
	public int m_userLengthIndex;
	//! m_dataLengthIndex:存储数据长度的数据段号
	public int m_dataLengthIndex;

	//! m_saveDataNum:数据段数
	public int m_saveDataNum;
	//! m_userDataIndex:数据段数的用户索引
	public int m_userDataIndex;

	//! m_userNum:用户数量
	public int m_userNum;
	int[] m_pBufD;

	//获取协议版本
	void GetVersion()
	{
		this.m_version.High = this.m_pBufD[1];
		this.m_version.Low = this.m_pBufD[0];
	}

	//! m_version:协议版本
	VERSION m_version;

	//! m_userName:用户姓名
	String m_userName;
	//! m_userIndex:用户姓名的用户索引
	public int m_userNameIndex;

	//! m_wBufDType:存放设备类型
	String m_wBufDType;
	//返回有PI存储数据
	public SpO2TrendPack SaveDataPI()
	{
		return m_savedataPI;
	}
	public SpO2PulsePack[] SaveData()
	{
		return m_savedata;
	}

	public int m_msgType;

	public String m_wBufDOnlyID;

	//! ValidBytes : 检查编码后的数据包是否有效

	//! @param buf : 待检查的数据
	//! @param len : 数据长度
	//! @return int 成功，返回数据包长度；数据错误返回小于0的整数, 返回0表示数据包不完整，需更多数据。
	//! @author yhl zhx
	//! @date   2009-8-22
	//! @note   无
	public static int ValidBytes( int[] buf, int len)
	{
		int result = -1;

		if (buf != null && len > 0)
		{
			int length = GetLength(buf[0]);

			if (length > len)
			{
				for(int i=0;i<30;i++)
				result = 0;
			}
			else if (0 == length)
			{
				result = -2;
			}
			else
			{
				result = length;

				for (int i = 1; i < length; ++i)
				{
					if ((buf[i] >> 7) != 1)
					{
						result = -2;
						break;
					}
				}
			}
		}

		return result;
	}

	//! PackBytes : 数据包高位字节的最高位调整到高位字节相应位置

	//! @param buf : 需移位的数据
	//! @param len : 数据长度
	//! @return int 数据包长度，无效返回0
	//! @author yhl zhx
	//! @date   2009-8-22
	//! @note   无
	public static int PackBytes( byte[]buf)
	{
		if (buf == null)
		{
			return 0;
		}

		int length = GetLength(buf[0]);

		if (length != 0)
		{
			buf[1] = (byte)0x80;

			for (int i = 2; i < length; ++i)
			{
				buf[1] |= (buf[i] & 0x80) >> (9 - i);
				buf[i] |= 0x80;
			}
		}

		return length;
	}

	//! UnpackBytes : 按协议将高位字节 buf[1]的位，放回原位

	//! @param buf : 需移位的数据
	//! @param len : 数据长度
	//! @return int 数据长度，无效返回0
	//! @author yhl zhx
	//! @date   2009-8-22
	//! @note   无
	public static int UnpackBytes(int[] buf, int len)
	{
		int length = ValidBytes(buf, len);

		for (int i = 2; i < length; ++i)
		{
			buf[i] &= (buf[1] << (9 - i)) | 0x7f;
		}

		return length;
	}


	//! 移动数据包，将数据包前两个无用字节除去
	boolean BufMove(int[]pBuf, int length)
	{
		if(length<=2)
			return false;
		m_pBufD = new int[length-2];
		for(int i=2;i<length;i++)
		{
			m_pBufD[i-2] = pBuf[i];
		}

		return true;
	}

	public int MsgType()
	{
		return m_msgType;
	}

	//返回用户数量
	public int UserNum()
	{
		return m_userNum;
	}

	//返回存储数据段数的用户索引
	public int UserDataIndex()
	{
		return m_userDataIndex;
	}

	//返回存储数据段数
	public int SaveDataNum()
	{
		return m_saveDataNum;
	}

	//! GetPackInfo : 解包函数

	//! @param pBuf : 需解包的数据
	//! @param len : 数据长度
	//! @return int 包类型 失败返回原因码
	//! @author spz
	//! @date   2009-8-22
	//! @note   无
	public  int GetPackInfo(int[] pBuf, int len)
	{

		int length = ValidBytes(pBuf, len);

		m_freeBack = 0;

		if (length > 0)
		{
			if (UnpackBytes(pBuf, length)>0)
			{
				BufMove(pBuf,length);
				switch (pBuf[0])
				{

				case e_pack_realtime://实时数据包
					int i;

					try {
						GetRealTimePackInfo();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case e_pack_devicetype://设备类型数据包
					{
						int nSize = 6;

						if (m_pBufD[0] == 0xFF)//第二包
						{

							String m_wBufDType1 = new String(m_pBufD ,1, nSize);
							m_wBufDType =m_wBufDType +m_wBufDType1;
							m_deviceinfo.DeviceType(m_wBufDType.trim());
						}
						else//第一包
						{
							m_wBufDType = new String( m_pBufD, 1, nSize );
							m_deviceinfo.SaveDataType(m_pBufD[0]);
						}
					}

					break;

				case e_pack_devicecomp://厂家标识
				{

					String wBufDcomp;
					//将单字节转换成宽字节
					wBufDcomp = new String(m_pBufD,0, length - 2);
					m_deviceinfo.DeviceCompany(wBufDcomp.trim());
					break;
				}

				case e_pack_deviceid://设备标识
				{

					String wBufDid;
					wBufDid = new String(m_pBufD,0, length - 2);
					m_deviceinfo.DeviceID(wBufDid.trim());

					break;
				}

				case e_pack_username://用户信息，第一位为用户索引号,此信息不存入设备信息
				{

					String wBufDname;
					m_userNameIndex = m_pBufD[0];
					wBufDname = new String(m_pBufD ,1, length - 3);
					m_userName = wBufDname.trim();

					break;
				}

				case e_pack_version://协议版本
					GetVersion();
					break;

				case e_pack_savedate://存储时间
					GetDateInfo();
					break;

				case e_pack_datalen://数据长度
					GetDataLength();
					break;

				case e_pack_saveDataPIType://存储数据PI类型
					GetDataPIType();
					break;

				case e_pack_savedatapi://有PI数据的存储数据包
					GetSaveDataPI();
					break;

				case e_pack_datanum://数据段数,第一位为用户索引号
					m_userDataIndex = m_pBufD[0];
					m_saveDataNum = m_pBufD[1];
					break;

				case e_pack_commerr://命令错误数据包
					m_command = m_pBufD[0];
					m_commandError = m_pBufD[1];
					break;

				case e_pack_freeback://空闲反馈数据包
					m_freeBack = 1;
					break;

				case e_pack_cutreas://断开通知数据包
					m_cutReason = m_pBufD[0];
					break;

				case e_pack_pitype://PI标识数据包 0：OK 1：无PI数据
					m_deviceinfo.PIType(m_pBufD[0]);
					break;

				case e_pack_savedata://无PI数据的存储数据信息
					GetSaveData();
					break;

				case e_pack_usernum://用户数量数据包
					m_userNum = m_pBufD[0];

					break;

				case e_pack_message:

					GetMsg();
					break;

				case e_pack_savetime:
					GetTimeInfo();
					break;

/*				case e_pack_onlyIDF:
					{
						const int nSize = 7;
						MultiByteToWideChar(CP_ACP, 0, static_cast<const char*>((const void*)(m_pBufD)), nSize, m_wBufDOnlyID, nSize);
					}
					break;

				case e_pack_onlyIDS:
					{
						const int nSize = 7;
						MultiByteToWideChar(CP_ACP, 0, static_cast<const char*>((const void*)(m_pBufD)), nSize, m_wBufDOnlyID + nSize, e_wBufSize - nSize);
					}
					break;
*/
				default:

					break;
				}
			}

			return pBuf[0];//解包成功返回包类型
		}

		return length;//解包失败返回 原因码
	}
	//有效范围上下限Spo2(0, 100], Pulse(0, 254], PI(0.00, 22.00]
	int SPO2UpLimit = 100;
	int SPO2LowLimit = 0;
	int PULSEUpLimit = 254;
	int PULSELowLimit = 0;
	int PIUpLimit = 2200;
	int PILowLimit = 0;
	circular_buffer<Integer> m_pulseBuf;
	AVAILDATE m_availDate = new AVAILDATE();
	boolean GetRealTimePackInfo() throws Exception
	{
		m_availDate.SearchTime = ((m_pBufD[0] >> 4 ) &0x01); //1：搜索时间过长 0：OK
		m_availDate.PulseSound = ((m_pBufD[0] >> 6) & 0x01); //1：脉搏声音提示 0：无
		m_availDate.DeviceError = ((m_pBufD[0] >> 7) & 0x01);//1：探头有错误 0：OK

		m_availDate.SearchPulse = ((m_pBufD[1] & 0x80) >> 7);//1：搜索脉搏 0：OK
		m_availDate.Wave = (m_pBufD[1] & 0x7F); //脉搏波形

		m_availDate.bPI = ((m_pBufD[2] & 0x10) >> 4); //1：PI数据无效 0：OK，
		m_availDate.Bar = (m_pBufD[2] & 0x0F); //棒图


		// 判断脉率相邻两包，不同则不处理。过滤设备发送的错误数据包。yhl 2010-04-26
		m_pulseBuf.push_back(m_pBufD[3]);//脉率

		if (m_pulseBuf.full())
		{
			boolean bSame = true;

			for ( int i = 1; i < m_pulseBuf.size(); ++i)
			{

			}

			if (bSame)
			{
				m_availDate.Pulse = m_pBufD[3];
			}
		}
		else
		{
			m_availDate.Pulse = e_pulse_exclude;
		}



		m_availDate.SpO2 =(m_pBufD[4]); //血氧饱和度

		int pi;
		pi = (m_pBufD[6]);//灌注指数fengtao
		pi = ((pi << 8) + m_pBufD[5]);
		m_availDate.PI = pi;

		if (m_availDate.Bar > 15)
		{
			m_availDate.Bar = 15;
		}

		{
			if (m_availDate.SpO2 > SPO2UpLimit  || m_availDate.SpO2 == SPO2LowLimit)
			{
				m_availDate.SpO2 = e_spO2_exclude;
			}

			if (m_availDate.SpO2 == SPO2UpLimit)
			{
				m_availDate.SpO2 = SPO2UpLimit - 1;
			}

			if (m_availDate.Pulse <= PULSELowLimit || m_availDate.Pulse > PULSEUpLimit)
			{
				m_availDate.Pulse = e_pulse_exclude;
			}

			if (((m_availDate.PI > PIUpLimit && m_availDate.PI < e_PI_exclude) || m_availDate.PI == PILowLimit)
					|| m_availDate.bPI == 1)//无PI数据
			{
				m_availDate.PI = e_PI_exclude;
			}

		}

		return true;
	}


	public static final int  e_spO2_exclude = 0x7f;
	public static final int e_pulse_exclude = 0xff;
	public static final int e_PI_exclude = 0xffff;

	public void GetSaveDataPI()
	{
		m_savedataPI._SpO2 = (byte) m_pBufD[0];
		m_savedataPI._Pulse = (byte) m_pBufD[1];
		int pi;
		pi = (m_pBufD[3]);
		pi = ((pi << 8) + m_pBufD[2]);


		m_savedataPI._PI = pi;


		if (m_savedataPI._SpO2 > SPO2UpLimit || m_savedataPI._SpO2 == SPO2LowLimit)//&& m_savedataPI._SpO2 < 0x7F)
		{
			m_savedataPI._SpO2 = e_spO2_exclude;
		}

		if (m_savedataPI._Pulse <= PULSELowLimit || m_savedataPI._Pulse > PULSEUpLimit)
		{
			m_savedataPI._Pulse = (byte)e_pulse_exclude;
		}

		if (((pi > PIUpLimit && pi < e_PI_exclude) || pi == PILowLimit)
					|| pi == 1)//无PI数据
		{
			m_savedataPI._PI = e_PI_exclude;
		}

	}




	public void GetSaveData()
	{
		m_savedata[0]._SpO2 = (byte) m_pBufD[0];
		m_savedata[0]._Pulse = (byte) m_pBufD[1];
		m_savedata[1]._SpO2 = (byte) m_pBufD[2];
		m_savedata[1]._Pulse = (byte) m_pBufD[3];
		m_savedata[2]._SpO2 = (byte) m_pBufD[4];
		m_savedata[2]._Pulse = (byte) m_pBufD[5];

		for (int i = 0; i < 3; i++)
		{
			if (m_savedata[i]._SpO2 > SPO2UpLimit || m_savedata[i]._SpO2 == SPO2LowLimit)//&& m_savedata[i]._SpO2 < 0x7F)
			{
				m_savedata[i]._SpO2 = e_spO2_exclude;
			}

			if (m_savedata[i]._Pulse <= PULSELowLimit || m_savedata[i]._Pulse > PULSEUpLimit)
			{
				m_savedata[i]._Pulse = (byte)e_pulse_exclude;
			}
		}
	}


	//! GetDateInfo : 获取存储数据的起始时间信息

	//! @return BOOL 解包成功
	//! @author spz
	//! @date   2009-8-22
	//! @note   2009-8-26 数据包中去掉年高位，秒位。第一、二位改为用户索引号和数据段号.
	//! @note   2010-1-5 设备存储数据时间由一包改为两包，此函数解包日期包
	boolean GetDateInfo()
	{
		int year = 0, month = 0, day = 0 ;
		m_userTimeIndex = m_pBufD[0];
		m_dataTimeIndex = m_pBufD[1];
		year = m_pBufD[2];
		year = year * 100 + m_pBufD[3];
		month = m_pBufD[4];
		day = m_pBufD[5];


		m_saveTime.SetSaveDate(year, month, day);//写入时间信息类
		return true;
	}


	boolean GetTimeInfo()
	{
		int hour = 0, minute = 0, second = 0,_if_upload=0;
		m_userTimeIndex = m_pBufD[0];
		m_dataTimeIndex = m_pBufD[1];
		hour = m_pBufD[2];
		minute = m_pBufD[3];
		second = m_pBufD[4];
		m_saveTime.SetSaveTime(hour, minute, second);//写入时间信息类
		_if_upload = m_pBufD[5];
		m_saveTime.saveIfUpload(_if_upload);
		return true;
	}


	//! GetDataLength :获取数据长度

	//! @return void
	//! @author spz
	//! @date   2009-8-22
	//! @note   无
	public void GetDataLength()
	{
		m_userLengthIndex = m_pBufD[0];
		m_dataLengthIndex = m_pBufD[1];
		m_saveDataLength = m_pBufD[5];
		m_saveDataLength = (m_saveDataLength << 8) | m_pBufD[4];
		m_saveDataLength = (m_saveDataLength << 8) | m_pBufD[3];
		m_saveDataLength = (m_saveDataLength << 8) | m_pBufD[2];

	}


	//! GetDataPIType :获取存储数据PI类型

	//! @return void
	//! @author yhl
	//! @date 2010-05-20
	//! @note 无
	public void GetDataPIType()
	{
		m_userLengthIndex = m_pBufD[0];
		m_dataLengthIndex = m_pBufD[1];
		m_saveDataPIType = m_pBufD[2];
	}


	public static final int[] length =
	{

		0, 9, 9, 9, 9, 9, 4, 8, 8, 6, 4, 4, 2, 3, 3, 8,	//0
		3, 9, 8, 9, 9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	//1
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	//2
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	//3
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //4
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //5
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //6
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 0, 0  //7
	};
	public static int GetLength(int datatype)
	{



		if ((datatype >> 7)!=0)//包类型的最高位永远为0，不为0错误
		{
			return 0;
		}

		return length[datatype];
	}
	public void GetMsg()
	{
		switch(m_pBufD[0])
		{
		case 0x01://设备存储数据状态
			m_msgType = 0x01;
			m_bHaveSaveData = (0 != m_pBufD[1]);
			break;
		default:
			break;
		}
	}
	public AVAILDATE AvailData() {
		// TODO Auto-generated method stub
		return this.m_availDate;
	}
}
