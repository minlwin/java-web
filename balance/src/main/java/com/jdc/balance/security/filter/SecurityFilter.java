package com.jdc.balance.security.filter;

import java.io.IOException;

import com.jdc.balance.security.LoginUser;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public abstract class SecurityFilter implements Filter{

	protected LoginUser loginIfo(ServletRequest request) {
		var session = ((HttpServletRequest) request).getSession();
		return (LoginUser) session.getAttribute("loginInfo");
	}
	
	protected void navigateToLogin(ServletRequest request, ServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("content", "/jsp/view/login.jsp");
		request.setAttribute("message", message);
		request.getServletContext().getRequestDispatcher("/jsp/template.jsp").forward(request, response);
	}
}
