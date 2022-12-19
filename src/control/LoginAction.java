package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginAction extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		String[] s = req.getCookies();  // Error: getCookies() in HttpServletRequest cannot be applied to given types
//		Cookie[] c = req.getCookies();
//		req.setAttribute("aValue","10");
//		req.setAttribute("anotherValue",10);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		User user = new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));

		UserDAO userDAO = new UserDAOImpl();
		int flag = -1;
		try	{
			flag = userDAO.login(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(user.getUsername());
		System.out.println(flag);

		if (flag == 1) {
			req.getSession().setAttribute("username", user.getUsername());
			res.sendRedirect("./welcome.jsp");
		}
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		if (flag == -1) {
			out.print("<script>alert('No username!'); window.location='login.jsp' </script>");
		}
		else {
			out.print("<script>alert('Wrong password!'); window.location='login.jsp' </script>");
		}
		out.flush();
		out.close();
	}
}