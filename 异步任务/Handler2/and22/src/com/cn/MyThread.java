package com.cn;

public class MyThread extends Thread{
	Main m_activity;
	int what = 1;//发送消息的what值
	public MyThread(Main m_activity){
		this.m_activity=m_activity;
	}
	public void run(){
		while(true){
			m_activity.myHandler.sendEmptyMessage((what++)%4);//发送消息
			try{
				Thread.sleep(2000);//睡眠
			}
			catch(Exception e){//捕获异常
				e.printStackTrace();//打印异常信息
			}
		}
	}

}
