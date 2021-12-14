package com.jdc.balance.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jdc.balance.model.domain.Transaction.Type;
import com.jdc.balance.model.vo.BalanceVO;
import com.jdc.balance.model.vo.Category;

public interface BalanceService {

	List<BalanceVO> search(LocalDate from, LocalDate to);
	
	Map<Type, Integer> getSummary();
	
	Map<Type, List<Category>> getCategorySummary();
	
}
