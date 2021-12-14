package com.jdc.message.model.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jdc.message.model.MessageAppException;
import com.jdc.message.model.dao.MemberDao;
import com.jdc.message.model.dto.Member;
import com.jdc.message.model.dto.SignUp;

public class MemberDaoImpl implements MemberDao {

	private DataSource dataSource;
	
	private static final String INSERT_SQL = """
			insert into member (login, name, password) values (?, ?, ?)
			""";
	
	private static final String UPDATE_SQL = """
			update member set name = ?, role = ? where login = ?
			""";

	private static final String FIND_ONE_SQL = """
			select login, name, role from member where login = ?
			""";

	public MemberDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void create(SignUp signUp) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(INSERT_SQL)) {
			
			stmt.setString(1, signUp.member().login());
			stmt.setString(2, signUp.member().name());
			stmt.setString(3, signUp.password());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MessageAppException(e.getMessage());
		}
	}

	@Override
	public void update(Member member) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(UPDATE_SQL)) {
			
			stmt.setString(1, member.name());
			stmt.setString(2, member.role());
			stmt.setString(3, member.login());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MessageAppException(e.getMessage());
		}
	}

	@Override
	public Member findOne(String login) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(FIND_ONE_SQL)) {
			
			stmt.setString(1, login);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new Member(rs.getString(1), rs.getString(12), rs.getString(3));
			}
			
		} catch (SQLException e) {
			throw new MessageAppException(e.getMessage());
		}
		
		return null;
	}

}
