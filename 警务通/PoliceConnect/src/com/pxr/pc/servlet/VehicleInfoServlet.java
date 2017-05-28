package com.pxr.pc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pxr.pc.pojo.VehicleInfo;
import com.pxr.pc.service.VehicleInfoService;
import com.pxr.pc.service.impl.VehicleInfoServiceImpl;

public class VehicleInfoServlet extends HttpServlet {

	VehicleInfo aVehicleInfo;
	VehicleInfoService aVehicleInfoService=new VehicleInfoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 req.setCharacterEncoding("UTF-8");
		aVehicleInfo=new VehicleInfo();
		aVehicleInfoService=new VehicleInfoServiceImpl();
		
		String name=req.getParameter("name");
		name=new String(name.getBytes("iso-8859-1"),"GBK");
		int idno=Integer.valueOf(req.getParameter("idno"));
		int license=Integer.valueOf(req.getParameter("license"));
		String faultRecord=req.getParameter("faultRecord");
		faultRecord=new String(faultRecord.getBytes("iso-8859-1"),"gb2312");
		System.out.println(faultRecord);
		double penally = 0;
		if(req.getParameter("penally")!=null){
			 penally=Double.valueOf(req.getParameter("penally"));
			}
		if(name!=null)aVehicleInfo.setName(name);
		if(idno!=0)aVehicleInfo.setIdno(idno);
		if(license!=0)aVehicleInfo.setLicense(license);
		if(faultRecord!=null)aVehicleInfo.setFaultRecord(faultRecord);
		if(penally!=0)aVehicleInfo.setPenally(penally);
		aVehicleInfo.setCreateTime( Calendar.getInstance().getTime().toString());
		int flag=aVehicleInfoService.insertVehicleInfo(aVehicleInfo);
		PrintWriter out = resp.getWriter();
		out.print(flag);
		out.close();
	
	
	}

	
}
