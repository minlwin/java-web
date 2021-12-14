package com.jdc.balance.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String category;

	private boolean approved;

	private Employee employee;

	private LocalDate date;

	private Type type;

	private List<TransactionDetails> details;
	
	public Transaction() {
		details = new ArrayList<>();
	}
	
	
	public int getItems() {
		return null == details ? 0 : details.stream().mapToInt(a -> a.getQuantity()).sum();
	}
	
	public int getTotal() {
		return null == details ? 0 : details.stream().mapToInt(a -> a.getTotal()).sum();
	}
	
	public boolean ownTransaction(String code) {
		if(null != employee) {
			return employee.getCode().equals(code);
		}
		
		return false;
	}

	public enum Type {
		Income, Expense
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<TransactionDetails> getDetails() {
		return details;
	}

	public void setDetails(List<TransactionDetails> details) {
		this.details = details;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}