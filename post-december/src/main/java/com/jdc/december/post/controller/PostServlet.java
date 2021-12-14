package com.jdc.december.post.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.jdc.december.post.model.PostDao;
import com.jdc.december.post.model.dto.MemberVO;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/details",
	"/post/edit",
	"/post/delete"
})
public class PostServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/postDS")
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var postId = req.getParameter("id");
		
		var view = switch(req.getServletPath()) {
		case "/details" -> "/views/details.jsp";
		case "/post/edit" -> "/views/edit.jsp";
		case "/post/delete" -> {
			if(null != postId && !postId.isEmpty()) {
				// search post from database by id
				var id = Integer.parseInt(postId);
				PostDao.getInstance(dataSource).delete(id);
			}
			yield null;
		}
		default -> null;
		};
		
		
		if(null == view) {
			resp.sendRedirect(getServletContext().getContextPath().concat("/home"));
		} else {
			if(null != postId && !postId.isEmpty() && !"0".equals(postId)) {
				// search post from database by id
				var id = Integer.parseInt(postId);
				var post = PostDao.getInstance(dataSource).findById(id);
				req.setAttribute("post", post);
			}
			
			getServletContext().getRequestDispatcher(view).forward(req, resp);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get User Input
		var postId = req.getParameter("id");
		var title = req.getParameter("title");
		var content = req.getParameter("content");

		var loginUser = (MemberVO)req.getSession().getAttribute("loginUser");
		
		
		// Save to Database
		var savedId = PostDao.getInstance(dataSource).save(postId, title, content, loginUser);
		
		
		// Redirect to Details View
		var redirectUrl = getServletContext().getContextPath().concat("/details?id=%d").formatted(savedId);
		resp.sendRedirect(redirectUrl);
	}

}
