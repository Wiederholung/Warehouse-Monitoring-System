package jspservlet.dao.impl;

import jspservlet.dao.UserDAO;
import jspservlet.vo.User;


public class UserDAOImpl implements UserDAO {


	public int queryByUsername(User user) throws Exception {
		// TODO Auto-generated method stub
		int flag = 0; 
  
        //����û�������û�����tom��������123����ô�ñ�־flagΪ1.   
        if(user.getUsername().equals("tom")&&user.getPassword().equals("123")){
               flag = 1;
            }   
		return flag;
	}
}

