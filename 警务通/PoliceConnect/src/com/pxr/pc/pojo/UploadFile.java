package com.pxr.pc.pojo;

public class UploadFile {
	/**
	 * �ϴ�Υ����Ϣ�ĸ��������ֳ���Ƭ��
	 */
	private int id;
	private String uploadTime ;//�ϴ�ʱ��
	private String fileDesc;//�ļ�����
	private String filePath;//�ϴ�·��
	
	public UploadFile(String uploadTime,String fileDesc,String Path){
		this.uploadTime=uploadTime;
		this.fileDesc=fileDesc;
		this.filePath=filePath;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
