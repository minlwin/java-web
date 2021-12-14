package com.jdc.balance.model.repo;

import java.util.*;
import java.util.function.Predicate;

import com.jdc.balance.model.domain.Employee;

public interface EmployeeRepo {

	List<Employee> search(Predicate<Employee> filter);

	Employee create(Employee data);

	Employee update(Employee data);

	Employee findByCode(String code);

	int employeeCount();

	int count();

}