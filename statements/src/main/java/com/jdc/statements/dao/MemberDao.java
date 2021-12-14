package com.jdc.statements.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdc.statements.ConnectionManager;
import com.jdc.statements.MessageDbException;
import com.jdc.statements.dto.Member;
import com.jdc.statements.dto.Member.Role;
import com.jdc.statements.utils.StringUtils;

public class MemberDao {

	private ConnectionManager manager;

	public MemberDao(ConnectionManager manager) {
		super();
		this.manager = manager;
	}
	
	public int createMember(Member member) {
		
		// Validations
		if(null == member) {
			throw new IllegalArgumentException();
		}
		
		// email
		if(StringUtils.isEmpty(member.email())) {
			throw new MessageDbException("Email must not be empty.");
		}
		
		// name
		if(StringUtils.isEmpty(member.name())) {
			throw new MessageDbException("Member name must not be empty.");
		}

		// password
		if(StringUtils.isEmpty(member.password())) {
			throw new MessageDbException("Password must not be empty.");
		}
		
		// dob
		if(null == member.dob()) {
			throw new MessageDbException("Date of birth must not be empty.");
		}
		
		try (var connectiion = manager.getConnection();
				var stmt = connectiion.prepareStatement("""
						insert into member values (?, ?, ?, ?, ?)
						""")) {
			
			stmt.setString(1, member.email());
			stmt.setString(2, member.name());
			stmt.setString(3, member.password());
			stmt.setDate(4, Date.valueOf(member.dob()));
			stmt.setString(5, member.role().name());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MessageDbException("Email has already been used.");
		}
	}
	
	public Member findByEmail(String email) {
		
		if(StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}
		
		try(var connection = manager.getConnection();
				var stmt = connection.prepareStatement("""
						select * from member where email = ?
						""")) {
			stmt.setString(1, email);
			
			var result = stmt.executeQuery();
			
			while(result.next()) {
				return new Member(
						result.getString("email"), 
						result.getString("name"), 
						result.getString("password"), 
						result.getDate("dob").toLocalDate(), 
						Role.valueOf(result.getString("role")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int changePassword(String email, String oldPass, String newPass) {
		
		// Validations
		if(StringUtils.isEmpty(email)) {
			throw new MessageDbException("Email must not be empty.");
		}
		
		if(StringUtils.isEmpty(oldPass)) {
			throw new MessageDbException("Old Password must not be empty.");
		}

		if(StringUtils.isEmpty(newPass)) {
			throw new MessageDbException("New Password must not be empty.");
		}
		
		if(oldPass.equals(newPass)) {
			throw new MessageDbException("New and Old password are the same passwords.");
		}
		
		try(var connection = manager.getConnection();
				var stmt = connection.prepareStatement("""
						select * from member where email = ?
						""", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			stmt.setString(1, email);
			
			var result = stmt.executeQuery();
			
			if(result.next()) {
				if(!oldPass.equals(result.getString("password"))) {
					throw new MessageDbException("Please check old password.");
				}
				
				result.updateString("password", newPass);
				result.updateRow();
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		throw new MessageDbException("Please check email.");
	}

}
