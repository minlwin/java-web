package com.jdc.balance.model;

import com.jdc.balance.model.service.impl.EmployeeServiceImpl;
import com.jdc.balance.model.service.impl.TransactionServiceImpl;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServiceManager implements ServletContextListener{
	
	public static final String EMPLOYEE_KEY = "com.jdc.employee";
	public static final String TRANSACTION_KEY = "com.jdc.transaction";
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		var storage = sce.getServletContext().getRealPath("data");
		
		// create Services
		var employees = new EmployeeServiceImpl(storage);
		employees.load();
		
		var transactions = new TransactionServiceImpl(storage);
		transactions.load();
		
		// add to application Scope
		sce.getServletContext().setAttribute(EMPLOYEE_KEY, employees);
		sce.getServletContext().setAttribute(TRANSACTION_KEY, transactions);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// save to file
		var employees = (Lifecycle)sce.getServletContext().getAttribute(EMPLOYEE_KEY);
		employees.save();
		
		var transactions = (Lifecycle)sce.getServletContext().getAttribute(TRANSACTION_KEY);
		transactions.save();
	}
	
	public static interface Lifecycle {
		void load();		
		void save();
	}
}
