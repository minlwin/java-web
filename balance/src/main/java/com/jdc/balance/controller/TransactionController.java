package com.jdc.balance.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jdc.balance.BaseController;
import com.jdc.balance.Destination;
import com.jdc.balance.model.domain.Transaction;
import com.jdc.balance.model.domain.Transaction.Type;
import com.jdc.balance.model.domain.TransactionDetails;
import com.jdc.balance.model.domain.Employee.Role;
import com.jdc.balance.utils.DateUtils;
import com.jdc.balance.utils.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/employee/transaction/search",
	"/employee/transaction/details",
	"/employee/transaction/edit",
	"/employee/transaction/save",
	"/manager/transaction/approve"
})
public class TransactionController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static final String LIST_VIEW = "employee/transaction";
	private static final String EDIT_VIEW = "employee/transaction-edit";
	private static final String DETAILS_VIEW = "employee/transaction-details";
	
	private static final String SEARCH_ACTION = "/employee/transaction/search";
	private static final String EDIT_ACTION = "/employee/transaction/edit";
	private static final String DETAILS_ACTION = "/employee/transaction/details";
	private static final String APPROVE_ACTION = "/manager/transaction/approve";
	

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		switch(path) {
		case SEARCH_ACTION -> search(req, resp);
		case DETAILS_ACTION -> showDetails(req, resp);
		case EDIT_ACTION -> edit(req, resp);
		case APPROVE_ACTION -> approve(req, resp);
		};
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// employee code from session
		var code = getLoginInfo(req).isManager() ? null : getLoginInfo(req).getProfile().getCode();
		
		// get request parameters
		var type = Type.valueOf(req.getParameter("type"));
		var from = DateUtils.stringToDate(req.getParameter("from"));
		
		if(null == from) {
			from = LocalDate.now().withDayOfMonth(1);
		}
		
		var to = DateUtils.stringToDate(req.getParameter("to"));
		var category = req.getParameter("category");
		
		// Search
		var list = transactionService().search(code, type, from, to, category);
		
		req.setAttribute("list", list);
		req.setAttribute("type", type);
		req.setAttribute("from", from);
		req.setAttribute("to", to);
		req.setAttribute("category", category);
		
		navigate(destinationBuilder(LIST_VIEW, "Income".equals(req.getParameter("type")))
				.req(req).resp(resp)
				.build());
	}

	private void showDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var idParam = req.getParameter("id");
		var data = transactionService().findById(Integer.parseInt(idParam));
		
		req.setAttribute("data", data);
		
		navigate(destinationBuilder(DETAILS_VIEW, Type.Income.equals(data.getType()))
				.req(req).resp(resp)
				.build());
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Transaction data = new Transaction();
		
		var idParam = req.getParameter("id");
		
		if(null != idParam  && !"0".equals(idParam)) {
			data = transactionService().findById(Integer.parseInt(idParam));
		}

		if(!isPostRequest(req)) {
			
			if(null == idParam) {
				data.setType(Type.valueOf(req.getParameter("type")));
				
				var code = getLoginInfo(req).getProfile().getCode();
				data.setEmployee(employeeService().findByCode(code));
				
				data.setDate(LocalDate.now());
				
				var details = new TransactionDetails();
				details.setQuantity(1);
				
				data.getDetails().add(details);
			}
			
			req.setAttribute("data", data);
			
			navigate(destinationBuilder(EDIT_VIEW, Type.Income.equals(data.getType()))
					.req(req).resp(resp)
					.build());
			
		} else {
			data.setCategory(req.getParameter("category"));
			data.setType(Type.valueOf(req.getParameter("type")));
			data.setDate(DateUtils.stringToDate(req.getParameter("date")));
			
			String code = req.getParameter("employeeCode");
			if(StringUtils.isEmpty(code)) {
				code = getLoginInfo(req).getProfile().getCode();				
			}
			
			data.setEmployee(employeeService().findByCode(code));
			
			if(Role.Manager.equals(data.getEmployee().getRole())) {
				data.setApproved(true);
			}
			
			var items = req.getParameterValues("item");
			var remarks = req.getParameterValues("remark");
			var prices = req.getParameterValues("price");
			var counts = req.getParameterValues("count");
			
			var details = new ArrayList<TransactionDetails>();
			for(var i = 0; i < items.length; i++) {
				var item = new TransactionDetails();
				item.setItem(items[i]);
				item.setRemark(remarks[i]);
				item.setPrice(Integer.parseInt(prices[i]));
				item.setQuantity(Integer.parseInt(counts[i]));
				details.add(item);
			}
			
			data.setDetails(details);
			
			data = transactionService().save(data);
			
			redirect(resp, "/employee/transaction/details?id=" + data.getId());
		}
	}

	private void approve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var idParam = req.getParameter("id");
		transactionService().approve(Integer.parseInt(idParam));
		redirect(resp, String.format("/employee/transaction/details?id=%s", idParam));
	}
	
	private Destination.Builder destinationBuilder(String view, boolean income) {
		Destination.Builder builder = new Destination.Builder().view(view);
		
		if(income) {
			builder.pageTitle("Incomes").activeMenu("incomes").viewTitle("Daily Incomes");
		} else {
			builder.pageTitle("Expenses").activeMenu("expenses").viewTitle("Daily Expenses");
		}
		
		return builder;
	}
	
}