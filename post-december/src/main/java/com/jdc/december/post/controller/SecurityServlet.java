package com.jdc.december.post.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.december.post.model.MemberDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/sign-in", 
	"/sign-up", 
	"/sign-out" 
})
public class SecurityServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/postDS")
	private DataSource dataSource;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var view = switch(req.getServletPath()) {
		case "/sign-in" -> "/views/signin.jsp";
		case "/sign-up" -> "/views/signup.jsp";
		case "/sign-out" -> {
			req.getSession().invalidate();
			yield null;
		}
		default -> null;
		};
		
		if(null == view) {
			resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
		} else {
			getServletContext().getRequestDispatcher(view).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var name = req.getParameter("name");
		var login = req.getParameter("login");
		var password = req.getParameter("password");
		
		var dao = MemberDao.getInstance(dataSource);
		
		switch(req.getServletPath()) {
		case "/sign-in" -> {
			req.login(login, password);
		}
		case "/sign-up" -> {
			dao.signUp(name, login, password);
			req.login(login, password);
		}
		}
		
		req.getSession(true).setAttribute("loginUser", dao.find(login));
		
		resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
	}

}
