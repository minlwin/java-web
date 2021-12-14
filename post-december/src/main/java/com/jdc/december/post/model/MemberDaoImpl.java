package com.jdc.december.post.model;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jdc.december.post.model.dto.Member.Role;
import com.jdc.december.post.model.dto.MemberVO;

class MemberDaoImpl implements MemberDao{

	private DataSource dataSource;
	
	private static final String FIND_SQL = "select * from member where login = ?";
	private static final String SIGNUP_SQL = "insert into member(name, login, password) values (?, ?, ?)";

	MemberDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public MemberVO find(String login) {
		
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(FIND_SQL)) {
			
			stmt.setString(1, login);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new MemberVO(
						rs.getString("login"), 
						rs.getString("name"), 
						Role.valueOf(rs.getString("role")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void signUp(String name, String login, String password) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(SIGNUP_SQL)) {
			
			stmt.setString(1, name);
			stmt.setString(2, login);
			stmt.setString(3, password);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
