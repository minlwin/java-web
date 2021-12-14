package com.jdc.balance.model.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import com.jdc.balance.model.ServiceManager.Lifecycle;
import com.jdc.balance.model.domain.Employee;
import com.jdc.balance.model.domain.Employee.Role;
import com.jdc.balance.model.repo.EmployeeRepo;
import com.jdc.balance.model.repo.impl.EmployeeRepoImpl;
import com.jdc.balance.model.service.BalanceBusinessException;
import com.jdc.balance.model.service.EmployeeService;
import com.jdc.balance.model.service.UserService;

public class EmployeeServiceImpl implements EmployeeService, UserService, Lifecycle {

	private EmployeeRepo repo;
	private String storage;
	private static final String FILE_NAME = "employee.dat";

	public EmployeeServiceImpl(String storage) {
		this.storage = storage;
		repo = new EmployeeRepoImpl();
	}

	@Override
	public void changePass(String code, String oldPass, String newPass, String confPass) {

		var employee = repo.findByCode(code);
				
		if (!oldPass.equals(employee.getPassword())) {
			throw new BalanceBusinessException("Please check your old password.");
		}
		
		if(oldPass.equals(newPass)) {
			throw new BalanceBusinessException("Old Password and New password are same passwords.");
		}
		
		if (!newPass.equals(confPass)) {
			throw new BalanceBusinessException("Please check your confirm password.");
		}

		employee.setPassword(newPass);
		repo.update(employee);
	}

	@Override
	public Employee login(String code, String pass) {
		var employee = repo.findByCode(code);

		if (null == employee) {
			throw new BalanceBusinessException("Please check logoin id.");
		}

		if (!pass.equals(employee.getPassword())) {
			throw new BalanceBusinessException("Please check your password.");
		}

		if (LocalDate.now().compareTo(employee.getRegistrationDate()) < 0) {
			throw new BalanceBusinessException("You can't use this system not yet.");
		}
		
		if (null != employee.getRetireDate() && LocalDate.now().compareTo(employee.getRetireDate()) > 0) {
			throw new BalanceBusinessException("You are retired from this system.");
		}

		return employee;
	}

	@Override
	public List<Employee> search(Role role, String name) {

		Predicate<Employee> filter = data -> true;

		if (null != role) {
			filter = filter.and(emp -> emp.getRole() == role);
		}

		if (null != name && !name.isEmpty()) {
			filter = filter.and(emp -> emp.getName().toLowerCase().startsWith(name.toLowerCase()));
		}

		return repo.search(filter);
	}
	
	@Override
	public int count() {
		return repo.count();
	}

	@Override
	public Employee save(Employee emp) {

		// Validation
		// name
		if (null == emp.getName() || emp.getName().isEmpty()) {
			throw new BalanceBusinessException("Please enter employee name.");
		}

		// role
		if (null == emp.getRole()) {
			throw new BalanceBusinessException("Please set employee role.");
		}

		// email
		if (null == emp.getEmail() || emp.getEmail().isEmpty()) {
			throw new BalanceBusinessException("Please enter email address.");
		}

		// registration date
		if (null == emp.getRegistrationDate()) {
			throw new BalanceBusinessException("Please enter registration date.");
		}

		if (null == emp.getCode() || emp.getCode().isEmpty()) {
			emp.setPassword(emp.getEmail());
			return repo.create(emp);
		}

		return repo.update(emp);
	}

	@Override
	public Employee findByCode(String code) {
		return repo.findByCode(code);
	}
	
	@Override
	public Employee saveProfileImage(String code, String imageFileName) {
		var employee = findByCode(code);
		employee.setProfileImage(imageFileName);
		return repo.update(employee);
	}


	@Override
	public void load() {

		try (var input = new ObjectInputStream(new FileInputStream(getDataFile()))) {
			var object = input.readObject();

			if (null != object) {
				repo = (EmployeeRepo) object;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (repo.employeeCount() == 0) {

			var manager = new Employee();
			manager.setRole(Role.Manager);
			manager.setName("Manager");
			manager.setEmail("manager@gmail.com");
			manager.setPhone("09110011001");
			manager.setRegistrationDate(LocalDate.now());

			save(manager);
		}
	}

	@Override
	public void save() {

		try (var output = new ObjectOutputStream(new FileOutputStream(getDataFile()))) {
			output.writeObject(repo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private File getDataFile() throws IOException {
		var file = new File(storage, FILE_NAME);
		if(!file.exists()) {
			var fileStorage = new File(storage);
			if(!fileStorage.exists()) {
				fileStorage.mkdir();
			}
			
			file.createNewFile();
		}
		
		return file;
	}


}