package com.pxr.pc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pxr.pc.dao.VehicleInfoDAO;
import com.pxr.pc.db.DataBaseConnection;
import com.pxr.pc.pojo.VehicleInfo;

public class VehicleInfoDAOImpl implements VehicleInfoDAO {
	private Connection conn = null;
	private DataBaseConnection dbc = null;
	
	public VehicleInfoDAOImpl(){
		dbc=new DataBaseConnection();
		this.conn=dbc.getConnection();
		
	}
	@Override
	public int InsertInfo(VehicleInfo vehicleInfo) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			String sql = "insert into vehicleinfotbl(name,idno,license,createtime,faultRecord,penally) values(?,?,?,?,?,?)" ;
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setString(1, vehicleInfo.getName()) ;
			pstmt.setInt(2, vehicleInfo.getIdno()) ;
			pstmt.setInt(3, vehicleInfo.getLicense()) ;
			pstmt.setString(4, vehicleInfo.getCreateTime()) ;
			pstmt.setString(5, vehicleInfo.getFaultRecord()) ;
			pstmt.setDouble(6, vehicleInfo.getPenally()) ;
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

}
