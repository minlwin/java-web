package com.jdc.statements.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.statements.ConnectionManager;
import com.jdc.statements.MessageDbException;
import com.jdc.statements.dto.Member;
import com.jdc.statements.dto.Message;
import com.jdc.statements.dto.Member.Role;
import com.jdc.statements.utils.StringUtils;

public class MessageDao {

	private ConnectionManager manager;
	private MemberDao memberDao;
	
	private static final String SELECT_SQL = """
			select ms.id id, ms.title title, ms.message message, ms.post_at postAt, ms.email email, 
			mb.name name, mb.role role, mb.password password, mb.dob dob 
			from message ms inner join member mb 
			on mb.email = ms.email 
			where 1 = 1
		""";

	public MessageDao(ConnectionManager manager) {
		super();
		this.manager = manager;
		memberDao = new MemberDao(manager);
	}
	
	public Message create(Message message) {
		
		validate(message);
		
		try(var conn = manager.getConnection();
				var stmt = conn.prepareStatement("""
						insert into message (email, title, message) 
						values (?, ?, ?)""", Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, message.member().email());
			stmt.setString(2, message.title());
			stmt.setString(3, message.message());
			
			stmt.executeUpdate();
			
			var result = stmt.getGeneratedKeys();
			if(result.next()) {
				return message.cloneWithId(result.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int save(Message message) {

		validate(message);
		
		try(var conn = manager.getConnection();
				var stmt = conn.prepareStatement("update message set title = ?, message = ? where id = ?")) {
			
			stmt.setString(1, message.title());
			stmt.setString(2, message.message());
			stmt.setInt(3, message.id());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private void validate(Message message) {
		if(null == message) {
			throw new IllegalArgumentException();
		}
		
		if(StringUtils.isEmpty(message.title())) {
			throw new MessageDbException("There is no title for message.");
		}
		
		if(StringUtils.isEmpty(message.message())) {
			throw new MessageDbException("There is no meessage body.");
		}
		
		if(null == message.member()) {
			throw new MessageDbException("There is no member for this message.");
		}

		if(null == memberDao.findByEmail(message.member().email())) {
			throw new MessageDbException("Invalid member for this message.");
		}		
	}
	
	public Message findById(int id) {
		
		try(var conn = manager.getConnection();
				var stmt = conn.prepareStatement(SELECT_SQL.concat(" and ms.id = ?"))) {
			
			stmt.setInt(1, id);
			
			var result = stmt.executeQuery();
			
			while(result.next()) {
				return reteriveData(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Message> searchByMember(String email) {
		
		if(null == email) {
			throw new IllegalArgumentException();
		}
		
		List<Message> list = new ArrayList<>();
		
		try(var conn = manager.getConnection();
				var stmt = conn.prepareStatement(SELECT_SQL.concat(" and mb.email = ?"))) {
			
			stmt.setString(1, email);
			
			var result = stmt.executeQuery();
			
			while(result.next()) {
				list.add(reteriveData(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<Message> search(String memberName, String keyword) {
		
		List<Message> list = new ArrayList<>();
		
		StringBuffer sb = new StringBuffer(SELECT_SQL);
		List<String> params = new ArrayList<>();
		
		if(!StringUtils.isEmpty(memberName)) {
			sb.append(" and lower(mb.name) like ?");
			params.add("%".concat(memberName).concat("%").toLowerCase());
		}
		
		if(!StringUtils.isEmpty(keyword)) {
			sb.append(" and (lower(ms.title) like ? or lower(ms.message) like ?)");
			params.add("%".concat(keyword).concat("%").toLowerCase());
			params.add("%".concat(keyword).concat("%").toLowerCase());
		}
		
		try(var conn = manager.getConnection();
				var stmt = conn.prepareStatement(sb.toString())) {
			
			for(int i = 0; i < params.size(); i ++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			var result = stmt.executeQuery();
			
			while(result.next()) {
				list.add(reteriveData(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private Message reteriveData(ResultSet result) throws SQLException {
		return new Message(
				result.getInt("id"), 
				result.getString("title"), 
				result.getString("message"), 
				result.getTimestamp("postAt").toLocalDateTime(), 
				new Member(
						result.getString("email"), 
						result.getString("name"), 
						result.getString("password"), 
						result.getDate("dob").toLocalDate(), 
						Role.valueOf(result.getString("role")))
				);
	}
	

}
