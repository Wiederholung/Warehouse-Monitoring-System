package JSPServlet.dao.impl;

import JSPServlet.dao.UserDAO;
import JSPServlet.vo.User;


public class UserDAOImpl implements UserDAO {


	public boolean queryByUsername(User user) {
		// TODO Auto-generated method stub
  
        //如果用户输入的用户名是tom，密码是123，那么置标志flag为1.   
		return user.getUsername().equals("tom") && user.getPassword().equals("123");
		}
	}

