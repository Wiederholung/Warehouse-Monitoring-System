package test.second.servlet;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import test.second.dao.UserDAO;
import test.second.dao.impl.UserDAOImpl;
import test.second.vo.User;

public class LoginServlet extends HttpServlet {
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
//		 User user = new User();
//		 user.setUsername(req.getParameter("username"));
//		 user.setPassword(req.getParameter("password"));
//		 
//		 UserDAO dao = new UserDAOImpl();   
//	     int flag = 0;
//	     try {
//				flag = dao.queryByUsername(user);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		} 
//		 if(flag == 1){   
//			 HttpSession session=req.getSession();   
//	         session.setAttribute("username", user.getUsername());   
//	         res.sendRedirect("./welcome.jsp");
//	        } else {   
//	         res.sendRedirect("./error.jsp");
//	        }
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
	 