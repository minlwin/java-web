package com.jdc.format;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/format")
public class FormatServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var number = 1092820.018273;
		var date = new Date();
		var localDate = LocalDate.now();
		var localDateTime = LocalDateTime.now();
		
		req.setAttribute("number", number);
		req.setAttribute("numberFloat", 0.0019);
		req.setAttribute("persentValue", 75);
		req.setAttribute("date", date);
		req.setAttribute("localDate", localDate);
		req.setAttribute("localDateTime", localDateTime);
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
