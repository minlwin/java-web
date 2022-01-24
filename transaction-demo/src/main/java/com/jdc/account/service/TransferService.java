package com.jdc.account.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdc.account.ConnectionManager;
import com.jdc.account.domain.TransferLog;

public class TransferService {

	public TransferLog transfer(int from, int to, int amount) {
		
		
		try(var conn = ConnectionManager.getConnection()) {
			
			try {
				// Start Transaction
				conn.setAutoCommit(false);
				
				// get amount from account from
				var fromAmount = getAmount(conn, from);
				
				// check amount
				if(fromAmount < amount) {
					throw new IllegalArgumentException("No enough amount to transfer.");
				}
				
				// get amount from account to
				var toAmount = getAmount(conn, to);
				
				// update from amount
				setAmount(conn, from, fromAmount - amount);
				
				// update to amount
				setAmount(conn, to, toAmount + amount);
				
				// insert into transfer log
				var logId = createTransferLog(conn, from, to, amount, fromAmount, toAmount);
				
				// select transfer log data
				var result = getTransferLog(conn, logId);
				
				// Commit at the end
				conn.commit();
				
				return result;

			} catch (SQLException e) {
				// Roll back when error
				conn.rollback();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private TransferLog getTransferLog(Connection conn, int logId) throws SQLException {
		
		var stmt = conn.prepareStatement("""
				select fa.name, ta.name, t.amount, t.transfer_time, 
				t.from_amount, t.to_amount from transfer_log t 
				join account fa on fa.id = t.from_account 
				join account ta on ta.id = t.to_account 
				where t.id = ?
				""");
		stmt.setInt(1, logId);
		
		
		var rs = stmt.executeQuery();
		
		while(rs.next()) {
			return new TransferLog(
					rs.getString(1), 
					rs.getString(2), 
					rs.getInt(3), 
					rs.getTimestamp(4).toLocalDateTime(), 
					rs.getInt(5), 
					rs.getInt(6));
		}
		
		return null;
	}

	private int  createTransferLog(Connection conn, int from, int to, int amount, int fromAmount, int toAmount) throws SQLException {
		
		var stmt = conn.prepareStatement("""
				insert into transfer_log (from_account, to_account, amount, from_amount, to_amount) 
				values (?, ?, ?, ?, ?)
				""", Statement.RETURN_GENERATED_KEYS);
		
		stmt.setInt(1, from);
		stmt.setInt(2, to);
		stmt.setInt(3, amount);
		stmt.setInt(4, fromAmount);
		stmt.setInt(5, toAmount);
		
		stmt.executeUpdate();
		
		var rs = stmt.getGeneratedKeys();
		
		while(rs.next()) {
			return rs.getInt(1);
		}
		
		return 0;
	}

	private void setAmount(Connection conn, int id, int amount) throws SQLException {
		var stmt = conn.prepareStatement("update account set amount = ? where id = ?");
		stmt.setInt(1, amount);
		stmt.setInt(2, id);
		stmt.executeUpdate();
	}

	private int getAmount(Connection conn, int id) throws SQLException{
		
		var stmt = conn.prepareStatement("select amount from account where id = ?");
		stmt.setInt(1, id);
		
		var rs = stmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("amount");
		}
		
		return 0;
	}
}
