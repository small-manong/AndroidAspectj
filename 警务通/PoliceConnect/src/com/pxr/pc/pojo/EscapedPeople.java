package com.pxr.pc.pojo;

public class EscapedPeople {
	/**
	 * 在逃人员类 
	 */
	private int id;
	private String name;//姓名
	private String gender;//性别
	private String age;//年龄
	private String idno;//身份证号
	private String address;//籍贯
	private String crimerecord;//犯罪记录
	private String pic;//图片路径
	
	public EscapedPeople(String name,String gender,String age,String idno,String address,String crimerecord,String pic){
		this.name=name;
		this.gender=gender;
		this.age=age;
		this.idno=idno;
		this.address=address;
		this.crimerecord=crimerecord;
		this.pic=pic;
		
	}
	public EscapedPeople() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCrimerecord() {
		return crimerecord;
	}
	public void setCrimerecord(String crimerecord) {
		this.crimerecord = crimerecord;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}	
	
}
