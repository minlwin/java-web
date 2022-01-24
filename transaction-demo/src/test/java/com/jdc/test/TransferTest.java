package com.jdc.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jdc.account.ConnectionManager;
import com.jdc.account.service.TransferService;

public class TransferTest {
	
	TransferService service;
	
	@BeforeEach
	void init() {
		service = new TransferService();
		
		try (var conn = ConnectionManager.getConnection()){
			
			conn.prepareStatement("set foreign_key_checks = 0").execute();

			conn.prepareStatement("truncate table transfer_log").execute();
			conn.prepareStatement("truncate table account").execute();
			conn.prepareStatement("insert into account (name, amount) values ('Aung Aung', 200000)").execute();
			conn.prepareStatement("insert into account (name, amount) values ('Thidar', 200000)").execute();
			
			conn.prepareStatement("set foreign_key_checks = 1").execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test() {
		
		var result = service.transfer(1, 2, 50000);
		assertNotNull(result);
		
		assertEquals("Aung Aung", result.from());
		assertEquals("Thidar", result.to());
		assertEquals(50000, result.amount());
		assertEquals(200000, result.fromAmount());
		assertEquals(200000, result.toAmount());
		
	}
}
