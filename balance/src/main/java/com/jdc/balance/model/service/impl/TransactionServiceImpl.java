package com.jdc.balance.model.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jdc.balance.model.ServiceManager.Lifecycle;
import com.jdc.balance.model.domain.Transaction;
import com.jdc.balance.model.domain.Transaction.Type;
import com.jdc.balance.model.repo.TransactionRepo;
import com.jdc.balance.model.repo.impl.TransactionRepoImpl;
import com.jdc.balance.model.service.BalanceBusinessException;
import com.jdc.balance.model.service.BalanceService;
import com.jdc.balance.model.service.TransactionService;
import com.jdc.balance.model.vo.BalanceVO;
import com.jdc.balance.model.vo.Category;
import com.jdc.balance.utils.StringUtils;

public class TransactionServiceImpl implements TransactionService, BalanceService, Lifecycle {

	private TransactionRepo repo;
	private String storage;
	private static final String FILE_NAME = "transactions.dat";
	
	public TransactionServiceImpl(String storage) {
		this.storage = storage;
		repo = new TransactionRepoImpl();
	}

	@Override
	public List<Transaction> search(String code, Type type, LocalDate from, LocalDate to, String category) {
		Predicate<Transaction> filter = data -> true;
		
		if(!StringUtils.isEmpty(code)) {
			filter = filter.and(data -> data.getEmployee().getCode().equals(code));
		}
		
		if(null != type) {
			filter = filter.and(data -> data.getType() == type);
		}
		
		if(null != from) {
			filter = filter.and(data -> data.getDate().compareTo(from) >= 0);
		}
		
		if(null != to) {
			filter = filter.and(data -> data.getDate().compareTo(to) <= 0);
		}
		
		if(null != category && !category.isEmpty()) {
			filter = filter.and(data -> data.getCategory().toLowerCase().startsWith(category.toLowerCase()));
		}
		
		return repo.search(filter);
	}
	
	@Override
	public List<BalanceVO> search(LocalDate from, LocalDate to) {
		Predicate<Transaction> filter = data -> data.isApproved();

		if(null != from) {
			filter = filter.and(data -> data.getDate().compareTo(from) >= 0);
		}
		
		if(null != to) {
			filter = filter.and(data -> data.getDate().compareTo(to) <= 0);
		}
		
		var transactions = repo.search(filter);
		
		var lastBalance = findLastBalance(from);

		return transactions.stream()
				.map(BalanceVO::new)
				.collect(ArrayList<BalanceVO>::new, 
					(list, data) -> {
						var balance = list.isEmpty() ? lastBalance.getBalance() : list.get(list.size() - 1).getBalance();
						data.setBalance(balance + data.getIncome() - data.getExpense());
						list.add(data);
					}, 
					ArrayList::addAll);
	}
	
	@Override
	public Map<Type, Integer> getSummary() {
		return repo.search(a -> true).stream().collect(Collectors.groupingBy(
				Transaction::getType, 
				Collectors.summingInt(Transaction::getTotal)
		));
	}
	
	@Override
	public Map<Type, List<Category>> getCategorySummary() {
		
		Map<Type, List<Category>> result = new HashMap<>();
		
		repo.search(a -> true).stream().collect(Collectors.groupingBy(
				Transaction::getType,
				Collectors.groupingBy(
						Transaction::getCategory,
						Collectors.summarizingInt(Transaction::getTotal)
				)
		)).forEach((key, value) -> {
			result.put(key, 
					value.entrySet().stream().map(Category::generate)
						.sorted((a, b) -> (int)(b.total() - a.total()))
						.toList());
				
		});
		
		return result;
	}

	@Override
	public Transaction findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Transaction save(Transaction data) {
		
		// date
		if(null == data.getDate()) {
			throw new BalanceBusinessException("Please enter transaction date.");
		}
		
		// type
		if(null == data.getType()) {
			throw new BalanceBusinessException("Please set transaction type.");
		}
		
		// category
		if(null == data.getCategory() || data.getCategory().isEmpty()) {
			throw new BalanceBusinessException("Please enter transaction category.");
		}
		
		// employee
		if(null == data.getEmployee()) {
			throw new BalanceBusinessException("Please set employee.");
		}
		
		// details
		if(null == data.getDetails() || data.getDetails().isEmpty()) {
			throw new BalanceBusinessException("Please enter transaction details.");
		}
		
		if(data.getId() == 0) {
			return repo.create(data);
		} 
		
		// TODO check to update 
		
		return repo.update(data);
	}

	@Override
	public void approve(int id) {
		var data = repo.findById(id);
		data.setApproved(true);
		repo.update(data);
	}

	@Override
	public void load() {

		try(var input = new ObjectInputStream(new FileInputStream(getDataFile()))) {
			var object = input.readObject();
			
			if(null != object) {
				repo = (TransactionRepo) object;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		
		try(var output = new ObjectOutputStream(new FileOutputStream(getDataFile()))) {
			output.writeObject(repo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private BalanceVO findLastBalance(LocalDate date) {
		var lastBalance = repo.search(data -> data.isApproved() && data.getDate().compareTo(date) < 0)
			.stream()
			.collect(
					Collectors.groupingBy(Transaction::getType, 
							Collectors.summingInt(Transaction::getTotal)));
		var result = new BalanceVO();
		
		result.setIncome(null == lastBalance.get(Type.Income)? 0 : lastBalance.get(Type.Income));
		result.setExpense(null == lastBalance.get(Type.Expense)? 0 : lastBalance.get(Type.Expense));
		result.setBalance(result.getIncome() - result.getExpense());
		
		return result;
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