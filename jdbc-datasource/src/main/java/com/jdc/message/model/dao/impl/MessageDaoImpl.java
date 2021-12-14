package com.jdc.message.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.message.model.MessageAppException;
import com.jdc.message.model.dao.MessageDao;
import com.jdc.message.model.dto.Member;
import com.jdc.message.model.dto.Message;

public class MessageDaoImpl implements MessageDao {

	private DataSource dataSource;
	
	private static final String SELECT = """
			select * from message ms left join member mb 
			on ms.member = mb.login where 1 = 1
			""";
	
	private static final String INSERT = """
			insert into message(title, message, member) values (?, ?, ?)
			""";

	private static final String DELETE = """
			delete from message where id = ?
			""";

	public MessageDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public Message findOne(int id) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(SELECT.concat(" and ms.id = ?"))) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getData(rs);
			}
			
		} catch (Exception e) {
			throw new MessageAppException(e.getMessage());
		}
		return null;
	}

	@Override
	public Message save(Message message) {
		if(message.id() == 0) {
			return create(message);
		}
		return null;
	}

	private Message create(Message message) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, message.title());
			stmt.setString(2, message.message());
			stmt.setString(3, message.member().login());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return findOne(rs.getInt(1));
			}

		} catch (Exception e) {
			throw new MessageAppException(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(int id) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(DELETE)) {
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new MessageAppException(e.getMessage());
		}
	}

	@Override
	public List<Message> search(String keyword) {
		var list = new ArrayList<Message>();
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(SELECT.concat("""
						 and (
						 lower(ms.title) like ? or 
						 lower(ms.message) like ? or 
						 lower(mb.name) like ?)
						"""))) {
			
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}
			
		} catch (Exception e) {
			throw new MessageAppException(e.getMessage());
		}

		return list;
	}

	private Message getData(ResultSet rs) throws SQLException {
		return new Message(
				rs.getInt("id"), 
				rs.getString("title"), 
				rs.getString("message"), 
				new Member(rs.getString("login"), rs.getString("name"), rs.getString("role")), 
				rs.getTimestamp("creation").toLocalDateTime());
	}

}
