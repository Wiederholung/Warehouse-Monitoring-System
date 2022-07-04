package model.dao;

import model.vo.User;

public interface UserDAO {	
	int queryByUsername(User user);
	boolean checkUsername(String username);
}
