package com.pxr.pc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pxr.pc.pojo.User;
import com.pxr.pc.service.UserService;
import com.pxr.pc.service.impl.UserServiceImpl;

public class UserLogin extends HttpServlet {
	private UserService userService;
	private User user;
	private int userExist;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		userService = new UserServiceImpl();
		user = new User();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		req.setAttribute("username", username);
		req.setAttribute("password", password);
		user.setUsername(username);
		user.setPassword(password);
		userExist = userService.findByUser(user);
		PrintWriter out = resp.getWriter();
		out.print(userExist);
		//"username:"+username+"password:"+password+":"+
		out.close();
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
