package com.pxr.pc.dao.factory;

import com.pxr.pc.dao.EscapedPeopleDAO;
import com.pxr.pc.dao.UserDAO;
import com.pxr.pc.dao.impl.EscapedPeopleDAOImpl;
import com.pxr.pc.dao.impl.UserDAOImpl;
import com.pxr.pc.dao.impl.VehicleInfoDAOImpl;

public class DAOFactory {

	public static UserDAO getIEmpDAOInstance() {
		return new UserDAOImpl();
	}

	public static EscapedPeopleDAO getEscapedPeopleDAOImpl() {

		return new EscapedPeopleDAOImpl();
	}

	public static VehicleInfoDAOImpl getVehicleInfoDAOImpl() {

		return new VehicleInfoDAOImpl();
	}
}
