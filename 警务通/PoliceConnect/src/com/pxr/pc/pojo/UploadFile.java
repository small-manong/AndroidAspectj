package com.pxr.pc.pojo;

public class UploadFile {
	/**
	 * 上传违章信息的附件：（现场照片）
	 */
	private int id;
	private String uploadTime ;//上传时间
	private String fileDesc;//文件描述
	private String filePath;//上传路径
	
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
