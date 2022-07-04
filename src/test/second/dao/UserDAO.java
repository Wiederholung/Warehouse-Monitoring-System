package test.second.dao;

import test.second.vo.User;

public interface UserDAO {	
	int queryByUsername(User user) throws Exception;

}
