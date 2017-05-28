package com.pxr.pc.dao;

import com.pxr.pc.pojo.User;

public interface UserDAO {

	public int findByUser(User aUser) throws Exception;
	
}
