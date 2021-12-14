package com.jdc.balance.utils;

import java.text.DecimalFormat;

public class StringUtils {
	
	private static final DecimalFormat INT_FMT = new DecimalFormat("#,##0");

	public static boolean isEmpty(String string) {
		return null == string || string.isEmpty();
	}
	
	public static String formatNumber(int data) {
		return INT_FMT.format(data);
	}
}
