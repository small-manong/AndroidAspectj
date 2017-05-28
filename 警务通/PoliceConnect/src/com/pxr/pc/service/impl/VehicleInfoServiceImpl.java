package com.pxr.pc.service.impl;

import com.pxr.pc.dao.VehicleInfoDAO;
import com.pxr.pc.dao.impl.VehicleInfoDAOImpl;
import com.pxr.pc.pojo.VehicleInfo;
import com.pxr.pc.service.VehicleInfoService;

public class VehicleInfoServiceImpl implements VehicleInfoService {
	VehicleInfoDAO aVehicleInfoDAO;
	
	
	@Override
	public int insertVehicleInfo(VehicleInfo vehicleInfo) {
		// TODO Auto-generated method stub
		int flag=0;
		aVehicleInfoDAO=new VehicleInfoDAOImpl();
		try {
			flag=aVehicleInfoDAO.InsertInfo(vehicleInfo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

}
