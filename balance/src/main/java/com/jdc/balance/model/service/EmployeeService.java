package com.jdc.balance.model.service;

import java.util.List;

import com.jdc.balance.model.domain.Employee;
import com.jdc.balance.model.domain.Employee.Role;

public interface EmployeeService {

	List<Employee> search(Role role, String name);

	Employee save(Employee emp);

	Employee findByCode(String code);
	
	int count();

}