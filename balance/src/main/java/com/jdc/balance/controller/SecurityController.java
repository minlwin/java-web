package com.jdc.balance.controller;

import java.io.IOException;

import com.jdc.balance.BaseController;
import com.jdc.balance.Destination;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/login",
	"/logout"
})
public class SecurityController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException, ServletException {
		switch(path) {
		case "/login" -> login(req, resp);
		case "/logout" -> logout(req, resp);
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		var loginPage = new Destination.Builder()
				.req(req).resp(resp)
				.pageTitle("Login")
				.view("login").build();
		
		if(isPostRequest(req)) {
			handleBusinessError(() -> {
				// Login Action
				var loginId = req.getParameter("loginId");
				var password = req.getParameter("password");
				
				var employee = userService().login(loginId, password);
				
				getLoginInfo(req).login(employee);
				
				redirect(resp, "/employee/home");
				
			}, loginPage);
		} else {
			navigate(loginPage);
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logout(req);
		redirect(resp, "/login");
	}

}