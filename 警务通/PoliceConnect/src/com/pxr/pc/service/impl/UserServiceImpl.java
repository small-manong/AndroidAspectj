package com.pxr.pc.service.impl;

import java.sql.SQLException;

import com.pxr.pc.dao.UserDAO;
import com.pxr.pc.dao.factory.DAOFactory;
import com.pxr.pc.pojo.User;
import com.pxr.pc.service.UserService;

public class UserServiceImpl implements UserService {
	UserDAO userDAOImpl=null;
	@Override
	public int findByUser(User user) {
		int flag=0;
		
		userDAOImpl=DAOFactory.getIEmpDAOInstance();
		try {
			flag=userDAOImpl.findByUser(user);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
