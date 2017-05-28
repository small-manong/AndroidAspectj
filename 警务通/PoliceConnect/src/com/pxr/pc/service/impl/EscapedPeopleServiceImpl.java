package com.pxr.pc.service.impl;

import com.pxr.pc.dao.EscapedPeopleDAO;
import com.pxr.pc.dao.factory.DAOFactory;
import com.pxr.pc.pojo.EscapedPeople;
import com.pxr.pc.service.EscapedPeopleService;

public class EscapedPeopleServiceImpl implements EscapedPeopleService{
	EscapedPeopleDAO aEscapedPeopleDaoImpl=null;
	public EscapedPeopleServiceImpl(){
		aEscapedPeopleDaoImpl=DAOFactory.getEscapedPeopleDAOImpl();
	}
	@Override
	public EscapedPeople findByIdno(String idno) {
		EscapedPeople aEscapedPeople=new EscapedPeople();
		
		aEscapedPeople=aEscapedPeopleDaoImpl.QueryByIdno(idno);
		return aEscapedPeople;
	}

}
