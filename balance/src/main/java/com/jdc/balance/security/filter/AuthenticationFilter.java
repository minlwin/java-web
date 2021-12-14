package com.jdc.balance.security.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName = "authFilter")
public class AuthenticationFilter extends SecurityFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		var loginInfo = loginIfo(request);
		
		if (null != loginInfo && loginInfo.isLogin()) {
			chain.doFilter(request, response);
		} else {
			navigateToLogin(request, response, "You need to login again.");
		}
	}

}
