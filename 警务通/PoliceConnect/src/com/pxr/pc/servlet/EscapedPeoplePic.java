package com.pxr.pc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pxr.pc.db.DataBaseConnection;
import com.pxr.pc.pojo.EscapedPeople;
import com.pxr.pc.service.EscapedPeopleService;
import com.pxr.pc.service.impl.EscapedPeopleServiceImpl;

public class EscapedPeoplePic extends HttpServlet {

	private EscapedPeople aEscapedPeople;
	private EscapedPeopleService aEscapedPeopleService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 req.setCharacterEncoding("GB2312");
         resp.setContentType("text/html;charset=GB2312");//设置Servlet页面类型和编码
         
		String idno = req.getParameter("idno");
		aEscapedPeople=new EscapedPeople();
		aEscapedPeopleService = new EscapedPeopleServiceImpl();
		aEscapedPeople = aEscapedPeopleService.findByIdno(idno);
		PrintWriter out = resp.getWriter();
		String info=aEscapedPeople.getPic();
		if(aEscapedPeople.getName()!=null){
			
		out.print(new String(info.getBytes("gb2312"),"gb2312"));
		out.close();
		}else{
			out.print(new String("没有犯罪记录".getBytes("gb2312"),"gbk"));
		}
		
		doPost(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}
	
}
