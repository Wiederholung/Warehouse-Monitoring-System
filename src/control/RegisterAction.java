package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.vo.User;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterAction extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        User user = new User("null", "null",
                "null", false);
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));

        UserDAO userDAO = new UserDAOImpl();
        boolean flag = false;
        try {
            flag = userDAO.registerWorker(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user.getUsername());

        if (flag) {
            res.sendRedirect("./login.jsp");
        }
        res.setCharacterEncoding("utf-8");
        PrintWriter out = res.getWriter();
        if (!flag) {
            out.print("<script>alert('Error'); window.location='register.jsp' </script>");
        }
        out.flush();
        out.close();
    }
}