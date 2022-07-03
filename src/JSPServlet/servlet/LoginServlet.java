package JSPServlet.servlet;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import JSPServlet.dao.UserDAO;
import JSPServlet.dao.impl.UserDAOImpl;
import JSPServlet.vo.User;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) {
	 }
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		 User user = new User();
		 user.setUsername(req.getParameter("username"));
		 user.setPassword(req.getParameter("password"));
		 
		 UserDAO dao = new UserDAOImpl();   
	     boolean judge = false;
	     try {judge = dao.queryByUsername(user);}
		 catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }

		 if(judge) {
		 	req.getSession().setAttribute("username", user.getUsername());
		 	res.sendRedirect("./welcome.jsp");
		 }
		 else {res.sendRedirect("./error.jsp");}
	 }
}
	 