package com.jdc.december.post.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.december.post.model.dto.Member.Role;
import com.jdc.december.post.model.dto.MemberVO;
import com.jdc.december.post.model.dto.Post;

class PostDaoImpl implements PostDao{

	private DataSource dataSource;
	
	private static final String SELECT_SQL = """
			select p.id id, p.title title, p.content content, p.creation creation,
				m.login login, m.name name, m.role role from post p inner join 
				member m on p.owner = m.login
			""";
	
	private static final String DELETE_SQL = "delete from post where id = ?";
	
	private static final String UPDATE_SQL = "update post set title = ?, content = ? where id = ?";
	private static final String INSERT_SQL = "insert into post(title, content, owner) values (?, ?, ?)";

	PostDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Post> search(String keyword) {
		
		var list = new ArrayList<Post>();
		var sb = new StringBuffer(SELECT_SQL);
		
		if(null != keyword  && !keyword.isEmpty()) {
			sb.append(" where lower(p.title) like ? or lower(p.content) like ?");
		}
		
		try(var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sb.toString())) {

			if(null != keyword  && !keyword.isEmpty()) {
				stmt.setString(1, "%".concat(keyword.toLowerCase()).concat("%"));
				stmt.setString(2, "%".concat(keyword.toLowerCase()).concat("%"));
			}
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public void delete(int id) {

		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(DELETE_SQL)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Post findById(int id) {

		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement("%s where p.id = ?".formatted(SELECT_SQL))) {
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getData(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int save(String postId, String title, String content, MemberVO loginUser) {
		if(null == postId || postId.isEmpty() || "0".equals(postId)) {
			return create(title, content, loginUser);
		}
		
		try(var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(UPDATE_SQL)) {
			
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setInt(3, Integer.parseInt(postId));
			
			return stmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
 		return 0;
	}
	
	private int create(String title, String content, MemberVO loginUser) {
		try(var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, loginUser.login());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	private Post getData(ResultSet rs) throws SQLException {
		return new Post(
				rs.getInt("id"), 
				rs.getString("title"), 
				rs.getString("content"),
				rs.getTimestamp("creation").toLocalDateTime(), 
				new MemberVO(
						rs.getString("login"), 
						rs.getString("name"), 
						Role.valueOf(rs.getString("role"))));
	}
	
}
