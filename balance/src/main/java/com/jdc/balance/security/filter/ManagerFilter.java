package com.jdc.balance.security.filter;

import java.io.IOException;

import com.jdc.balance.model.domain.Employee.Role;
import com.jdc.balance.security.LoginUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName = "managerFilter")
public class ManagerFilter extends SecurityFilter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		LoginUser loginUser = loginIfo(request);

		if(Role.Manager.match(loginUser.getRole())) {
			chain.doFilter(request, response);
		} else {
			loginUser.logout();
			navigateToLogin(request, response, "You have no authority to use Manager Function.");
		}		
	}

}
