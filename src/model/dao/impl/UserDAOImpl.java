package model.dao.impl;

import model.dao.UserDAO;
import model.vo.User;


public class UserDAOImpl implements UserDAO {

	@Override
	public int queryByUsername(User user){

		int flag = 0; 
  
        //如果用户输入的用户名是tom，密码是123，那么置标志flag为1.   
        if (user.getUsername().equals("tom")) {
			flag = 1;
			if(user.getPassword().equals("123")){
				flag=2;//判断是否用户民存在以及在用户名存在的情况下密码是否正确
			}
		}
		return flag;
	}

	@Override
	public boolean checkUsername(String username) {
		return false;
	}
}

