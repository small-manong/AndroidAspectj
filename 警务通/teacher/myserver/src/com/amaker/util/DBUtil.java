package com.amaker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * 
 *	���ݿ⹤����
 */
public class DBUtil {
	
	/*
	 * �ر����ݿ�����
	 */
	public void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * �����ݿ�����
	 */
	public Connection openConnection() {
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;

		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream(
					"DBConfig.properties"));

			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) throws Exception{
		Connection con=new DBUtil().openConnection();
		PreparedStatement ps=con.prepareStatement("select * from UserTbl  ");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getInt(1));
		}
	}

}
