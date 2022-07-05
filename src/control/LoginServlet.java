package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;


public class LoginServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		User user = new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));

		UserDAO userDAO = new UserDAOImpl();

		int flag = userDAO.login(user);
		if (flag == 1) {
			req.getSession().setAttribute("username", user.getUsername());
			res.sendRedirect("./welcome.jsp");
		}
		else if (flag == -1) res.sendRedirect("./errorName.jsp");
		else res.sendRedirect("./errorPass.jsp");
	}
}