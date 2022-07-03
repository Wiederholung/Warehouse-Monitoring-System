package jspservlet.dao.impl;

import jspservlet.dao.UserDAO;
import jspservlet.vo.User;


public class UserDAOImpl implements UserDAO {


	public boolean queryByUsername(User user) throws Exception {
		// TODO Auto-generated method stub
  
        //如果用户输入的用户名是tom，密码是123，那么置标志flag为1.   
		return user.getUsername().equals("tom") && user.getPassword().equals("123");
		}
	}

