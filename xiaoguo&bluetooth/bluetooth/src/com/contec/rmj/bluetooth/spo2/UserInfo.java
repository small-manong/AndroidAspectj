package com.contec.rmj.bluetooth.spo2;

import java.io.OutputStream;

public class UserInfo {

	private long m_size;	//!< 结构大小
	private long m_version; //!< 版本�?
	private long m_height;
	private long m_weight;
	private long m_age;
	private long m_sex;     //!< 1 : male, 2 : female
	private String m_chName;      //!< 姓名
	private String m_chComment="";   //!< 评论
	private  String m_chNationality="";//!< 国籍



	public UserInfo()
	{
		m_version = 1;
		this.m_size = 856;
	}


	public long Size()  { return m_size; }		//!< 读取结构大小

	public void Size(long v)  { m_size = v; }	//!< 写入结构大小

	public long Version()  { return m_version; }		//!< 读取版本�?

	void Version( long v)  { m_version = v; }	//!< 写入版本�?

	public long Height()  { return m_height; }		//!< 读取身高

	public void Height(long v)  { m_height = v; }	//!< 写入身高

	public long Weight()  { return m_weight; }		//!< 读取体重

	public void Weight( long v)  { m_weight = v; }	//!< 写入体重

	public long Age()  { return m_age; }		//!< 读取年龄

	public void Age( long v)  { m_age = v; }	//!< 写入年龄

	public long Sex()  { return m_sex; }		//!< 读取性别 1 : male, 2 : female

	public void Sex( long v)  { m_sex = v; }	//!< 写入性别 1 : male, 2 : female

	public String Name()  { return m_chName; }  //!< 读取姓名

	public void Name(String name)
	{
		m_chName = name;
	}

	public String Comment()
	{
		return m_chComment;
	}

	public void Comment(String comment)
	{
		m_chComment = comment;
	}

	public String Nationa()  { return m_chNationality; }  //!< 读取国籍

	public void Nationa( String nationa )		     //!< 写入国籍
	{
		this.m_chNationality = nationa;
	}
	public boolean writeToFile(OutputStream out)
	{
		Util.writeLong(out, this.m_size);
//		PageUtil.print(this.m_size+"-u8");
		Util.writeLong(out, this.m_version);
//		PageUtil.print(this.m_version+"-u7");
		Util.writeLong(out, this.m_height*100);
//		PageUtil.print(this.m_height+"-u6");
		Util.writeLong(out, this.m_weight);
//		PageUtil.print(this.m_weight+"-u5");
		Util.writeLong(out, this.m_age);
//		PageUtil.print(this.m_age+"-u4");
		Util.writeLong(out, this.m_sex);
//		PageUtil.print(this.m_sex+"-u3");
		Util.writeString(out, this.m_chName, 128);
//		PageUtil.print(this.m_chName+"-u2");
		Util.writeString(out, this.m_chComment, 256);
//		PageUtil.print(this.m_chComment+"-u1");
		Util.writeString(out, this.m_chNationality, 32);
//		PageUtil.print(this.m_chNationality+"-u-chty");
		return true;
	}

}
