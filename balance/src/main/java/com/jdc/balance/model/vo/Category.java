package com.jdc.balance.model.vo;

import java.util.IntSummaryStatistics;
import java.util.Map;

public record Category(
		String name,
		int min,
		int max,
		long count,
		long total) {
	
	public static Category generate(Map.Entry<String, IntSummaryStatistics> e) {
		var name = e.getKey();
		var stas = e.getValue();
		return new Category(name, stas.getMin(), stas.getMax(), stas.getCount(), stas.getSum());
	}
}
