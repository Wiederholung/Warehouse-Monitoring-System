package jspservlet.dao;

import jspservlet.vo.User;

public interface UserDAO {	
	boolean queryByUsername(User user) throws Exception;

}
