package com.jdc.december.post.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.december.post.model.PostDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/postDS")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("list", 
				PostDao.getInstance(dataSource).search(req.getParameter("keyword")));
		
		getServletContext().getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
}
