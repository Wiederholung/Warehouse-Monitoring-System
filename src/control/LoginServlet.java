package control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		User user = new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));

		UserDAO dao = new UserDAOImpl();

		int flag = dao.queryByUsername(user);
		if (flag == 1) {
			req.getSession().setAttribute("username", user.getUsername());
			res.sendRedirect("./welcome.jsp");
		}
		else if (flag == 0) res.sendRedirect("./error.jsp");
		else res.sendRedirect("./iderror.jsp");
	}
}