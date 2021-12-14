package com.jdc.balance.security;

import java.io.Serializable;

import com.jdc.balance.model.domain.Employee;
import com.jdc.balance.model.domain.Employee.Role;

public class LoginUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	
	public UserProfile getProfile() {
		return employee;
	}
	
	public boolean isLogin() {
		return null != employee;
	}
	
	public boolean isManager() {
		return null != employee && employee.getRole() == Role.Manager;
	}

	public void logout() {
		employee = null;
	}
	
	public String getProfileImage() {
		
		if(null != employee && null != employee.getProfileImage()) {
			return employee.getProfileImage();
		}
		
		return "user-profile.png";
	}

	public void login(Employee employee) {
		this.employee = employee;
	}
	
	public String getName() {
		if(null != employee) {
			return employee.getName();
		}
		
		return "";
	}
	
	public String getRole() {
		if(null != employee) {
			return employee.getRole().name();
		}
		
		return "";
	}
	

}
