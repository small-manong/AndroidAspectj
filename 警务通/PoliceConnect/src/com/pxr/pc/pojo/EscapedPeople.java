package com.pxr.pc.pojo;

public class EscapedPeople {
	/**
	 * ������Ա�� 
	 */
	private int id;
	private String name;//����
	private String gender;//�Ա�
	private String age;//����
	private String idno;//���֤��
	private String address;//����
	private String crimerecord;//�����¼
	private String pic;//ͼƬ·��
	
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
