package com.pxr.pc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 数据库的连接操作类
public class DataBaseConnection {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/policedb";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "psr";
	private Connection conn = null;

	public DataBaseConnection() {
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.conn;
	}
	public void close(){
		try {
			this.conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
