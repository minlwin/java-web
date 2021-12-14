package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.datababse.ConnectionManager;
import com.jdc.datababse.MyFirstDao;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyFirstTest {
	
	private MyFirstDao dao;
	
	@BeforeAll
	static void initDatabase() {
		System.out.println("Before All : Init Database");
	}
	
	@AfterAll
	static void backUpDatabase() {
		System.out.println("After All : Backup Database");
	}
	
	@BeforeEach
	void initBeforeTestCase() {
		dao = new MyFirstDao(ConnectionManager.getInstance());
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");
	}

	@Test
	@Order(2)
	void courseCountTest() {
		var result = dao.getCourseCount();
		
		assertEquals(5, result);
		
		System.out.println("Course Count Test");
	}
	
	@Test
	@Order(1)
	void getAllCourseTest() {
		System.out.println("Get All Course Test");
	}

}
