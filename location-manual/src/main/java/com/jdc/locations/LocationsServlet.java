package com.jdc.locations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = "/locations"
)
public class LocationsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/LocationDB")
	private DataSource dataSource;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var list = new ArrayList<>();
		
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement("select * from state")) {
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var data = new Location(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6)
					);
				list.add(data);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		req.setAttribute("list", list);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
