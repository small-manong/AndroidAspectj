package com.cn;

public class MyThread extends Thread{
	Main m_activity;
	int what = 1;//������Ϣ��whatֵ
	public MyThread(Main m_activity){
		this.m_activity=m_activity;
	}
	public void run(){
		while(true){
			m_activity.myHandler.sendEmptyMessage((what++)%4);//������Ϣ
			try{
				Thread.sleep(2000);//˯��
			}
			catch(Exception e){//�����쳣
				e.printStackTrace();//��ӡ�쳣��Ϣ
			}
		}
	}

}
