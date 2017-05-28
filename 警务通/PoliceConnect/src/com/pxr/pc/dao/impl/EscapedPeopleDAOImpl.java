package com.pxr.pc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import com.pxr.pc.dao.EscapedPeopleDAO;
import com.pxr.pc.db.DataBaseConnection;
import com.pxr.pc.pojo.EscapedPeople;

public class EscapedPeopleDAOImpl implements EscapedPeopleDAO{
	
	private Connection conn = null;
	private DataBaseConnection dbc = null;
	
	public EscapedPeopleDAOImpl(){
		dbc=new DataBaseConnection();
		this.conn=dbc.getConnection();
		
	}
	@Override
	public EscapedPeople QueryByIdno(String idno) {
		// TODO Auto-generated method stub
		
		/**
		 * 按照身份证号来查询
		 * @return  
		 */
		java.sql.PreparedStatement pstmt = null;
		EscapedPeople aEscapedPeople=new EscapedPeople();
		try {
			String sql = "SELECT * FROM escapedpeopletabl WHERE idno=?" ;
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setString(1, idno) ;
			ResultSet rs = pstmt.executeQuery() ;
			if(rs.first()){
				aEscapedPeople.setName(rs.getString(2));
				aEscapedPeople.setAge(rs.getString(4));
				aEscapedPeople.setGender(rs.getString(3));
				aEscapedPeople.setAddress(rs.getString(6));
				aEscapedPeople.setPic(rs.getString(8));
				aEscapedPeople.setCrimerecord(rs.getString(7));
				
			}else{
				aEscapedPeople.setCrimerecord("无犯罪记录");
			}
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return aEscapedPeople;
	}

}
