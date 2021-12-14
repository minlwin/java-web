package com.jdc.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.domain.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var list = List.of(
				new Student(1, "Aung Aung", "0998777899", "aung@gmail.com"),
				new Student(2, "Maung Maung", "0998777898", "maung@gmail.com"),
				new Student(3, "Thidar", "0998777897", "thidar@gmail.com"),
				new Student(4, "Nilar", "0998777896", "nilar@gmail.com")
		);
		
		req.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
