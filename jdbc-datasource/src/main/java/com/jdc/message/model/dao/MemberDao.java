package com.jdc.message.model.dao;

import javax.sql.DataSource;

import com.jdc.message.model.dao.impl.MemberDaoImpl;
import com.jdc.message.model.dto.Member;
import com.jdc.message.model.dto.SignUp;

public interface MemberDao {

	void create(SignUp signUp);
	void update(Member member);
	Member findOne(String login);
	
	static MemberDao getInstance(DataSource ds) {
		return new MemberDaoImpl(ds);
	}
}
