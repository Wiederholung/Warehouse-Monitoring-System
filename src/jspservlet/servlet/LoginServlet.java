package jspservlet.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jspservlet.dao.UserDAO;
import jspservlet.dao.impl.UserDAOImpl;
import jspservlet.vo.User;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
	 }
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
		 User user = new User();
		 user.setUsername(req.getParameter("username"));
		 user.setPassword(req.getParameter("password"));
		 
		 UserDAO dao = new UserDAOImpl();   
	     int flag = 0;
	     try {
				flag = dao.queryByUsername(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 
		 if(flag == 1){   
			 HttpSession session=req.getSession();   
	         session.setAttribute("username", user.getUsername());   
	         res.sendRedirect("./welcome.jsp");
	        } else {   
	         res.sendRedirect("./error.jsp");
	        }
	 }
}
	 