package com.jdc.statments.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.statements.ConnectionManager;
import com.jdc.statements.MessageDbException;
import com.jdc.statements.dao.MemberDao;
import com.jdc.statements.dto.Member;
import com.jdc.statements.dto.Member.Role;
import com.jdc.statments.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
class MemberDaoTest {
	
	
	MemberDao dao;
	static Member input;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseInitializer.truncate("message", "member");
		input = new Member("minlwin@gmail.com", "Min Lwin", "minlwin", LocalDate.of(1975, 1, 28), Role.Admin);
	}

	@BeforeEach
	void setUp() throws Exception {
		dao = new MemberDao(ConnectionManager.getInstance());
	}

	@Test
	@Order(1)
	void testCreateMemberOK() {
		var result = dao.createMember(input);
		assertEquals(1, result);
	}

	@Test
	@Order(1)
	void testCreateMemberDuplicateKey() {
		var exception = assertThrows(
				MessageDbException.class, 
				() -> dao.createMember(input));
		assertEquals("Email has already been used.", exception.getMessage());
	}

	@Test
	@Order(3)
	void testCreateMemberNull() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> dao.createMember(null));
	}

	@Test
	@Order(4)
	void testCreateMemberNullName() {
		var nullData = new Member("test@gmail.com", null, "minlwin", LocalDate.of(2000, 01, 01), Role.Admin);
		var exception = assertThrows(MessageDbException.class, () -> dao.createMember(nullData));
		assertEquals("Member name must not be empty.", exception.getMessage());

		var emptyData = new Member("test@gmail.com", "", "minlwin", LocalDate.of(2000, 01, 01), Role.Admin);
		exception = assertThrows(MessageDbException.class, () -> dao.createMember(emptyData));
		assertEquals("Member name must not be empty.", exception.getMessage());
	}

	@Test
	@Order(5)
	void testCreateMemberNullEmail() {
		var nullData = new Member(null, "Min Lwin", "minlwin", LocalDate.of(2000, 01, 01), Role.Admin);
		var exception = assertThrows(MessageDbException.class, () -> dao.createMember(nullData));
		assertEquals("Email must not be empty.", exception.getMessage());

		var emptyData = new Member("", "Min Lwin", "minlwin", LocalDate.of(2000, 01, 01), Role.Admin);
		exception = assertThrows(MessageDbException.class, () -> dao.createMember(emptyData));
		assertEquals("Email must not be empty.", exception.getMessage());
	}

	@Test
	@Order(6)
	void testCreateMemberNullPassword() {
		var nullData = new Member("test@gmail.com", "Min Lwin", null, LocalDate.of(2000, 01, 01), Role.Admin);
		var exception = assertThrows(MessageDbException.class, () -> dao.createMember(nullData));
		assertEquals("Password must not be empty.", exception.getMessage());

		var emptyData = new Member("test@gmail.com", "Min Lwin", "", LocalDate.of(2000, 01, 01), Role.Admin);
		exception = assertThrows(MessageDbException.class, () -> dao.createMember(emptyData));
		assertEquals("Password must not be empty.", exception.getMessage());
	}

	@Test
	@Order(7)
	void testCreateMemberNullDob() {
		var nullData = new Member("test@gmail.com", "Min Lwin", "admin", null, Role.Admin);
		var exception = assertThrows(MessageDbException.class, () -> dao.createMember(nullData));
		assertEquals("Date of birth must not be empty.", exception.getMessage());
	}

	@Test
	@Order(8)
	void testFindByEmail() {
		var result = dao.findByEmail(input.email());
		assertEquals(input, result);
	}

	@Test
	@Order(9)
	void testFindByEmailNotFound() {
		var result = dao.findByEmail("%s1".formatted(input.email()));
		assertNull(result);
	}

	@Test
	@Order(10)
	void testFindByEmailNullData() {
		assertThrows(IllegalArgumentException.class, () -> dao.findByEmail(null));
	}

	@Test
	@Order(11)
	void testChangePassword() {
		var newPass = "newPassword";
		var result = dao.changePassword(input.email(), input.password(), newPass);
		assertEquals(1, result);
		
		var member = dao.findByEmail(input.email());
		assertEquals(newPass, member.password());
		assertEquals(input.role(), member.role());
		assertEquals(input.name(), member.name());
		assertEquals(input.dob(), member.dob());
	}

	@Test
	@Order(12)
	void testChangePasswordNotFound() {
		var exception = assertThrows(MessageDbException.class, () -> dao.changePassword("%s1".formatted(input.email()), input.password(), "newPass"));
		assertEquals("Please check email.", exception.getMessage());
	}

	@Test
	@Order(13)
	void testChangePasswordNullEmail() {
		var exception = assertThrows(MessageDbException.class, () -> dao.changePassword(null, "newPassword", "newPass"));
		assertEquals("Email must not be empty.", exception.getMessage());
		exception = assertThrows(MessageDbException.class, () -> dao.changePassword("", "newPassword", "newPass"));
		assertEquals("Email must not be empty.", exception.getMessage());
	}
	
	@Test
	@Order(14)
	void testChangePasswordNullOldPass() {
		var exception = assertThrows(MessageDbException.class, () -> dao.changePassword(input.email(), null, "newPass"));
		assertEquals("Old Password must not be empty.", exception.getMessage());
		exception = assertThrows(MessageDbException.class, () -> dao.changePassword(input.email(), "", "newPass"));
		assertEquals("Old Password must not be empty.", exception.getMessage());
	}
	
	@Test
	@Order(15)
	void testChangePasswordNullNewPass() {
		var exception = assertThrows(MessageDbException.class, () -> dao.changePassword(input.email(), "newPassword", null));
		assertEquals("New Password must not be empty.", exception.getMessage());
		exception = assertThrows(MessageDbException.class, () -> dao.changePassword(input.email(), "newPassword", ""));
		assertEquals("New Password must not be empty.", exception.getMessage());
	}
	
	@Test
	@Order(16)
	void testChangePasswordUnMatchPass() {
		var exception = assertThrows(MessageDbException.class, () -> dao.changePassword(input.email(), input.password(), "newPass"));
		assertEquals("Please check old password.", exception.getMessage());
	}
	
	@Test
	@Order(17)
	void testChangePasswordSamePasswords() {
		var exception = assertThrows(MessageDbException.class, () -> dao.changePassword(input.email(), "newPassword", "newPassword"));
		assertEquals("New and Old password are the same passwords.", exception.getMessage());
	}
}
