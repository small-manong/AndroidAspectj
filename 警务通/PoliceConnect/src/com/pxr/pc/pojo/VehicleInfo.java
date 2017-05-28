package com.pxr.pc.pojo;

public class VehicleInfo {
	/**
	 * ������Υ����Ϣ��
	 */

	private int id;
	private String name;// ����
	private int idno;// ���֤����
	private int license;// �����պ���
	private String createTime;// �ü�¼���ʱ��
	private String faultRecord;// Υ�¼�¼����
	private double penally;// ������

	public VehicleInfo(String name, int idno, int license, String createTime,
			String faultRecord, double penally) {
		this.name=name;
		this.idno=idno;
		this.license=license;
		this.createTime=createTime;
		this.faultRecord=faultRecord;
		this.penally=penally;
		
	}

	public VehicleInfo() {
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

	public int getIdno() {
		return idno;
	}

	public void setIdno(int idno) {
		this.idno = idno;
	}

	public int getLicense() {
		return license;
	}

	public void setLicense(int license) {
		this.license = license;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFaultRecord() {
		return faultRecord;
	}

	public void setFaultRecord(String faultRecord) {
		this.faultRecord = faultRecord;
	}

	public double getPenally() {
		return penally;
	}

	public void setPenally(double penally) {
		this.penally = penally;
	}

}
