package com.jdc.message.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.message.model.dao.MessageDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/home",
	"/message/add",
	"/message/edit",
	"/message/delete"
})
public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/MessageDB")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var dao = MessageDao.getInstance(dataSource);
		
		var view = switch(req.getServletPath()) {
		case "/home" -> {
			req.setAttribute("list", dao.search(req.getParameter("keyword")));
			yield "/home.jsp";
		}
		case "/message/add", "/message/edit" -> {
			var id = req.getParameter("id");
			if(null != id && !id.isEmpty()) {
				req.setAttribute("data", dao.findOne(Integer.parseInt(id)));
			}
			yield "/message-edit.jsp";
		}
		case "/message/delete" -> {
			var id = req.getParameter("id");
			if(null != id && !id.isEmpty()) {
				dao.delete(Integer.parseInt(id));
			}
			yield null;
		}
		default -> null;
		};
		
		if(null == view) {
			resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
		} else {
			getServletContext().getRequestDispatcher(view).forward(req, resp);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
