package com.jdc.custom.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.custom.controller.model.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/load")
public class LoadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("courses", List.of("Java Basic", "Spring", "Java EE", "Fullstack Angular", "AWS", "Flutter", "Fullstack React"));
		req.setAttribute("members", List.of(
				new Member(1, "Admin User", "Admin", "0976667889", "admin@gmail.com"),
				new Member(2, "Aung Aung", "Student", "0976667887", "aung@gmail.com"),
				new Member(3, "Thidar", "Student", "0976667886", "thidar@gmail.com"),
				new Member(4, "Nilar", "Teacher", "0976667885", "nilar@gmail.com"),
				new Member(5, "Maung Maung", "Student", "0976667883", "maung@gmail.com")
				));
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
