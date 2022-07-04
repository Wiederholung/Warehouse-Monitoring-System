package first.dao;

import first.vo.User;

public interface UserDAO {	
	boolean queryByUsername(User user);

}
