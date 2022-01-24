package com.jdc.account.domain;

import java.time.LocalDateTime;

public record TransferLog(
		String from, 
		String to, 
		int amount, 
		LocalDateTime time, 
		int fromAmount, 
		int toAmount) {
	
}
