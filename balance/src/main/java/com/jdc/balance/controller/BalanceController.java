package com.jdc.balance.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.jdc.balance.BaseController;
import com.jdc.balance.Destination;
import com.jdc.balance.utils.DateUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/manager/balance"
})
public class BalanceController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		search(req, resp);
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var from = DateUtils.stringToDate(req.getParameter("from"));
		
		if(null == from) {
			from = LocalDate.now().withDayOfMonth(1);
		}
		
		var to = DateUtils.stringToDate(req.getParameter("to"));
		
		// search
		var list = balanceService().search(from, to);
		
		req.setAttribute("list", list);
		req.setAttribute("from", from);
		req.setAttribute("to", to);

		navigate(new Destination.Builder()
				.req(req).resp(resp)
				.view("manager/reports")
				.pageTitle("Reports")
				.viewTitle("Balance Report")
				.activeMenu("reports").build());
	}

}