package model.dao;

import model.vo.User;

public interface UserDAO {
	boolean doseUserExist(User user);

	boolean checkPasswordStrength(User user);

	int checkInfo(User user);

	int login(User user);

	int register(User user);
}
