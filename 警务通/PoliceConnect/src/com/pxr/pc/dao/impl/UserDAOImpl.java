package com.pxr.pc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import com.pxr.pc.dao.UserDAO;
import com.pxr.pc.db.DataBaseConnection;
import com.pxr.pc.pojo.User;

public class UserDAOImpl implements UserDAO {
	
	private Connection conn = null;
	private DataBaseConnection dbc = null;
	
	public UserDAOImpl(){
		dbc=new DataBaseConnection();
		this.conn=dbc.getConnection();
		
	}
	
	@Override
	public int findByUser(User aUser) throws Exception {
		/**
		 * 按照实体类来查询
		 * @return 查询有是1，没有是0
		 */
		int flag=0;
		java.sql.PreparedStatement pstmt = null;
		try {
			String sql = "SELECT * FROM usertable WHERE username=? AND password=?" ;
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setString(1, aUser.getUsername()) ;
			pstmt.setString(2, aUser.getPassword()) ;
			ResultSet rs = pstmt.executeQuery() ;
			if(rs.first()){
				flag=1;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				throw e;
			}
		}
		return flag;
	}

}
