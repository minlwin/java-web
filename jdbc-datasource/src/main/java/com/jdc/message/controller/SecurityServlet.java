package com.jdc.message.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.message.model.dao.impl.MemberDaoImpl;
import com.jdc.message.model.dto.Member;
import com.jdc.message.model.dto.SignUp;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/signin", "/signout", "/signup" })
public class SecurityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/MessageDB")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var view = switch (req.getServletPath()) {
		case "/signout" -> {
			req.getSession().invalidate();
			yield null;
		}
		case "/signup" -> "/login.jsp?type=up";
		default -> "/login.jsp?type=in";
		};

		if (null == view) {
			req.getSession().invalidate();
			resp.sendRedirect(getServletContext().getContextPath());
		} else {
			getServletContext().getRequestDispatcher(view).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			var dao = new MemberDaoImpl(dataSource);
			
			var loginId = req.getParameter("loginId");
			var password = req.getParameter("password");
			
			switch (req.getServletPath()) {
			case "/signin" -> {
				req.login(loginId, password);
			}
			case "/signup" -> {
				dao.create(new SignUp(new Member(loginId, req.getParameter("name"), "Member"), password));
			}
			}
			
			req.getSession(true).setAttribute("loginUser", dao.findOne(loginId));

			resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
