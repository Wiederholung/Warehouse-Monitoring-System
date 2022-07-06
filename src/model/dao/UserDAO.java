package model.dao;

import model.vo.User;

public interface UserDAO {
	boolean doseUserExist(User user);

	boolean checkPasswordStrength(User user);

	int checkInfo(User user);

	int login(User user);

	int addManger(User manger, User newManager);
	boolean addWorker(User manger, User newWorker);
	boolean delWorker(User worker);
}
