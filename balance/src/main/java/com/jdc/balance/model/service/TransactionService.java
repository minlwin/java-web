package com.jdc.balance.model.service;

import java.time.LocalDate;
import java.util.List;

import com.jdc.balance.model.domain.Transaction;
import com.jdc.balance.model.domain.Transaction.Type;

public interface TransactionService {

	public List<Transaction> search(String code, Type type, LocalDate from, LocalDate to, String category);

	public Transaction findById(int id);

	public Transaction save(Transaction data);

	public void approve(int id);

}