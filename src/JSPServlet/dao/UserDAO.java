package JSPServlet.dao;

import JSPServlet.vo.User;

public interface UserDAO {	
	boolean queryByUsername(User user);

}
