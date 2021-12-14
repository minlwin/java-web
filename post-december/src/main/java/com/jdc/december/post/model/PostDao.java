package com.jdc.december.post.model;

import java.util.List;

import javax.sql.DataSource;

import com.jdc.december.post.model.dto.MemberVO;
import com.jdc.december.post.model.dto.Post;

public interface PostDao {
	
	static PostDao getInstance(DataSource ds) {
		return new PostDaoImpl(ds);
	}

	List<Post> search(String keyword);

	void delete(int id);

	Post findById(int id);

	int save(String postId, String title, String content, MemberVO loginUser);

}
