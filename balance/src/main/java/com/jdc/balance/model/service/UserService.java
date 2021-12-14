package com.jdc.balance.model.service;

import com.jdc.balance.model.domain.Employee;

public interface UserService {

	void changePass(String code, String oldPass, String newPass, String confPass);

	Employee login(String code, String pass);
	
	Employee saveProfileImage(String code, String imageFileName);

}