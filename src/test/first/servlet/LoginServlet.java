package test.first.servlet;


import test.first.dao.UserDAO;
import test.first.dao.impl.UserDAOImpl;
import test.first.vo.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) {
	 }
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException {
		 User user = new User();
		 user.setUsername(req.getParameter("username"));
		 user.setPassword(req.getParameter("password"));
		 
		 UserDAO dao = new UserDAOImpl();   
	     boolean flag = false;
	     try {
				flag = dao.queryByUsername(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 
		 if(flag){
			 HttpSession session=req.getSession();   
	         session.setAttribute("username", user.getUsername());   
	         res.sendRedirect("./welcome.jsp");
	        } else {   
	         res.sendRedirect("./error.jsp");
	        }
	 }
}
	 