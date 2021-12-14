package com.jdc.statments;

import java.sql.SQLException;

import com.jdc.statements.ConnectionManager;

public class DatabaseInitializer {
	
	public static void truncate(String ... tables) {
		
		try(var connection = ConnectionManager.getInstance().getConnection()) {
			
			var stmt = connection.createStatement();
			stmt.execute("set foreign_key_checks = 0");
			
			for(var table : tables) {
				stmt.execute("truncate table %s".formatted(table));
			}
			
			stmt.execute("set foreign_key_checks = 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
