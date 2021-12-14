package com.jdc.balance.model.vo;

import java.time.LocalDate;

import com.jdc.balance.model.domain.Employee;
import com.jdc.balance.model.domain.Transaction;
import com.jdc.balance.model.domain.Transaction.Type;

public class BalanceVO {

	private int id;
	private String category;
	private Employee employee;
	private LocalDate date;
	private int income;
	private int expense;
	private int balance;
	
	public BalanceVO() {
	}

	public BalanceVO(Transaction trans) {
		this.id = trans.getId();
		this.category = trans.getCategory();
		this.employee = trans.getEmployee();
		this.date = trans.getDate();
		if(trans.getType().equals(Type.Income)) {
			this.income = trans.getTotal();
		} else {
			this.expense = trans.getTotal();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
