package com.jdc.balance;

import java.io.IOException;

import com.jdc.balance.model.ServiceManager;
import com.jdc.balance.model.service.BalanceBusinessException;
import com.jdc.balance.model.service.BalanceService;
import com.jdc.balance.model.service.EmployeeService;
import com.jdc.balance.model.service.TransactionService;
import com.jdc.balance.model.service.UserService;
import com.jdc.balance.security.LoginUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected abstract void process(HttpServletRequest req, HttpServletResponse resp, String path)
			throws ServletException, IOException;

	protected void navigate(Destination destination) throws ServletException, IOException {
		var content = String.format("/jsp/view/%s.jsp", destination.getView());
		destination.getReq().setAttribute("content", content);

		destination.getReq().setAttribute("pageTitle", destination.getPageTitle());
		destination.getReq().setAttribute("viewTitle", destination.getViewTitle());
		destination.getReq().setAttribute("activeMenu", destination.getActiveMenu());

		getServletContext().getRequestDispatcher("/jsp/template.jsp").forward(destination.getReq(), destination.getResp());
	}

	protected void redirect(HttpServletResponse resp, String path) throws IOException {
		resp.sendRedirect(getServletContext().getContextPath().concat(path));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp, req.getServletPath());
	}

	protected void handleBusinessError(Action action, Destination errorDestination) throws ServletException, IOException {
		try {
			action.execute();
		} catch (BalanceBusinessException e) {
			errorDestination.getReq().setAttribute("message", e.getMessage());
			navigate(errorDestination);
		} 
	}

	protected LoginUser getLoginInfo(HttpServletRequest req) {
		LoginUser loginInfo = (LoginUser) req.getSession(true).getAttribute("loginInfo");

		if (null == loginInfo) {
			loginInfo = new LoginUser();
			req.getSession().setAttribute("loginInfo", loginInfo);
		}

		return loginInfo;
	}

	protected void logout(HttpServletRequest req) {
		getLoginInfo(req).logout();
	}

	protected boolean isPostRequest(HttpServletRequest req) {
		return "POST".equals(req.getMethod());
	}
	
	protected TransactionService transactionService() {
		return (TransactionService) getServletContext().getAttribute(ServiceManager.TRANSACTION_KEY);
	}
	
	protected BalanceService balanceService() {
		return (BalanceService) getServletContext().getAttribute(ServiceManager.TRANSACTION_KEY);
	}
	
	protected UserService userService() {
		return (UserService) getServletContext().getAttribute(ServiceManager.EMPLOYEE_KEY);
	}
	
	protected EmployeeService employeeService() {
		return (EmployeeService) getServletContext().getAttribute(ServiceManager.EMPLOYEE_KEY);
	}
	
	
	protected interface Action {
		void execute() throws IOException;
	}
	
}	