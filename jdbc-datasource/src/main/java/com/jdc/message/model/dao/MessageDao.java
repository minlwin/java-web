package com.jdc.message.model.dao;

import java.util.List;

import javax.sql.DataSource;

import com.jdc.message.model.dao.impl.MessageDaoImpl;
import com.jdc.message.model.dto.Message;

public interface MessageDao {

	Message findOne(int id);
	
	Message save(Message message);
	
	void delete(int id);
	
	List<Message> search(String keyword);
	
	static MessageDao getInstance(DataSource ds) {
		return new MessageDaoImpl(ds);
	}

}
