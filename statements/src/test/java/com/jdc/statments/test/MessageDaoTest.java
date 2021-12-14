package com.jdc.statments.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.statements.ConnectionManager;
import com.jdc.statements.MessageDbException;
import com.jdc.statements.dao.MessageDao;
import com.jdc.statements.dto.Member;
import com.jdc.statements.dto.Member.Role;
import com.jdc.statements.dto.Message;
import com.jdc.statments.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
class MessageDaoTest {
	
	MessageDao dao;
	static Message message;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// Truncate Tables
		DatabaseInitializer.truncate("member", "message");
		
		// Create Test Members
		try(var conn = ConnectionManager.getInstance().getConnection();
				var stmt = conn.prepareStatement("insert into member values (?, ?, ?, ?, ?)")) {
			
			stmt.setString(1, "found@gmail.com");
			stmt.setString(2, "Founded User");
			stmt.setString(3, "Founded User");
			stmt.setDate(4, Date.valueOf("2000-01-01"));
			stmt.setString(5, Role.Member.name());
			
			stmt.addBatch();
			
			stmt.setString(1, "notfound@gmail.com");
			stmt.setString(2, "Not Found User");
			stmt.setString(3, "Not Found User");
			stmt.setDate(4, Date.valueOf("2000-01-01"));
			stmt.setString(5, Role.Member.name());
			
			stmt.addBatch();
			
			stmt.executeBatch();
		} 
		
		message = new Message("Test Title", "Test Message", 
					new Member("found@gmail.com", "Founded User", "Founded User", LocalDate.of(2000, 1, 1), Role.Member)
				);
	}

	@BeforeEach
	void setUp() throws Exception {
		dao = new MessageDao(ConnectionManager.getInstance());
	}

	@Test
	@Order(1)
	void testCreate() {
		var result = dao.create(message);
		assertEquals(1, result.id());
	}

	@Test
	@Order(2)
	void testCreateNull() {
		assertThrows(IllegalArgumentException.class, () -> dao.create(null));
	}

	@Test
	@Order(3)
	void testCreateNullMember() {
		var nullMember = new Message("Titlte", "Message", null);
		var exception = assertThrows(MessageDbException.class, () -> dao.create(nullMember));
		assertEquals("There is no member for this message.", exception.getMessage());
	}

	@Test
	@Order(4)
	void testCreateInvalidMember() {
		var invalidMember = new Message("Titlte", "Message", new Member("abbbb", "Invalid Member", "Invalid", null, null));
		var exception = assertThrows(MessageDbException.class, () -> dao.create(invalidMember));
		assertEquals("Invalid member for this message.", exception.getMessage());
	}

	@Test
	@Order(5)
	void testCreateNulTitle() {
		var invalidMessage = new Message(null, "Message", message.member());
		var exception = assertThrows(MessageDbException.class, () -> dao.create(invalidMessage));
		assertEquals("There is no title for message.", exception.getMessage());

		var emptyMessage = new Message("", "Message", message.member());
		exception = assertThrows(MessageDbException.class, () -> dao.create(emptyMessage));
		assertEquals("There is no title for message.", exception.getMessage());
	}

	@Test
	@Order(6)
	void testCreateNulMessage() {
		var invalidMessage = new Message("Message", null, message.member());
		var exception = assertThrows(MessageDbException.class, () -> dao.create(invalidMessage));
		assertEquals("There is no meessage body.", exception.getMessage());

		var emptyMessage = new Message("Message", "", message.member());
		exception = assertThrows(MessageDbException.class, () -> dao.create(emptyMessage));
		assertEquals("There is no meessage body.", exception.getMessage());
	}

	@Test
	@Order(7)
	void testFindById() {
		var result = dao.findById(1);
		assertNotNull(result);
		assertEquals(message.title(), result.title());
		assertEquals(message.message(), result.message());
		assertEquals(message.member(), result.member());
	}

	@Test
	@Order(8)
	void testFindByIdNotFound() {
		var result = dao.findById(2);
		assertNull(result);
	}

	@Test
	@Order(9)
	void testSave() {
		var target = dao.findById(1);
		target = target.cloneWithTitle("New Title").cloneWithMessage("New Message");
		var result = dao.save(target);
		assertEquals(1, result);
		assertEquals(target, dao.findById(1));
	}

	@Test
	@Order(10)
	void testSaveNull() {
		assertThrows(IllegalArgumentException.class, () -> dao.save(null));
	}

	@Test
	@Order(11)
	void testSaveNullTitle() {
		var nullTitle = dao.findById(1).cloneWithTitle(null).cloneWithMessage("New Message");
		var exception = assertThrows(MessageDbException.class, () -> dao.save(nullTitle));
		assertEquals("There is no title for message.", exception.getMessage());

		var emptyTitle = dao.findById(1).cloneWithTitle(null).cloneWithMessage("New Message");
		exception = assertThrows(MessageDbException.class, () -> dao.save(emptyTitle));
		assertEquals("There is no title for message.", exception.getMessage());
	}

	@Test
	@Order(12)
	void testSaveNullMessage() {
		var nullTitle = dao.findById(1).cloneWithTitle("New Title").cloneWithMessage(null);
		var exception = assertThrows(MessageDbException.class, () -> dao.save(nullTitle));
		assertEquals("There is no meessage body.", exception.getMessage());

		var emptyTitle = dao.findById(1).cloneWithTitle("New Title").cloneWithMessage("");
		exception = assertThrows(MessageDbException.class, () -> dao.save(emptyTitle));
		assertEquals("There is no meessage body.", exception.getMessage());
	}

	@Test
	@Order(13)
	void testSearchByMember() {
		var result = dao.searchByMember(message.member().email());
		assertEquals(1, result.size());
	}

	@Test
	@Order(14)
	void testSearchByMemberNull() {
		assertThrows(IllegalArgumentException.class, () -> dao.searchByMember(null));
	}

	@Test
	@Order(15)
	void testSearchByMemberNotFound() {
		var result = dao.searchByMember(message.member().email().concat(" "));
		assertEquals(0, result.size());
	}

	@Test
	@Order(16)
	void testSearch() {
		var result = dao.search("founded", "message");
		assertEquals(1, result.size());
	}

	@Test
	@Order(17)
	void testSearchWithEmptyArguments() {
		var result = dao.search(null, null);
		assertEquals(1, result.size());
	}
	
	@Test
	@Order(18)
	void testSearchWithEmptyMember() {
		var result = dao.search("", "title");
		assertEquals(1, result.size());
	}
	
	@Test
	@Order(19)
	void testSearchWithEmptyKeyword() {
		var result = dao.search("founded", "");
		assertEquals(1, result.size());
	}

	@Test
	@Order(20)
	void testSearchWithNotFound() {
		var result = dao.search("ended", "");
		assertEquals(0, result.size());
	}
}
