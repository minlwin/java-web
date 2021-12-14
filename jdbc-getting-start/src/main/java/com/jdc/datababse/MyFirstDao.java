package com.jdc.datababse;

import java.sql.SQLException;

public class MyFirstDao {
	
	private static String SQL = "select count(*) from course";
	
	private ConnectionManager manager;
	
	public MyFirstDao(ConnectionManager manager) {
		this.manager = manager;
	}
	
	
	public long getCourseCount() {
		
		try(var connection = manager.getConnection();
				var statement = connection.createStatement()) {
			
			var resultSet = statement.executeQuery(SQL);
			
			while(resultSet.next()) {
				return resultSet.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
