package test.second.dao.impl;

import test.first.dao.UserDAO;
import test.first.vo.User;
import java.sql.Connection;
import java.sql.DriverManager;


public class UserDAOImpl implements UserDAO {
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver"; // 不用更改
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/lianxi?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8"; // 见上面的解释
	private static final String DATABASE_USER = "root"; // 用户名
	private static final String DATABASE_PASSWORD = "1234"; // 自己的密码

	public boolean queryByUsername(User user) {
		// TODO Auto-generated method stub

		//如果用户输入的用户名是tom，密码是123，那么置标志flag为1.
		return (user.getUsername().equals("tom") && user.getPassword().equals("123"));
	}
}

