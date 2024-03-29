package com.jdc.balance.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static String dateToString(LocalDate date) {
		if(null != date) {
			return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
		}
		
		return "";
	}
	
	public static LocalDate stringToDate(String str) {
		if(null != str && !str.isEmpty()) {
			return LocalDate.parse(str, DateTimeFormatter.ofPattern(DATE_FORMAT));
		}
		
		return null;
	}
}
