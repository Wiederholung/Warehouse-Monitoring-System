package model.dao;

import model.vo.User;

import java.util.ArrayList;

public interface UserDAO {
	User getUser(String username) throws Exception;

	void addUser(User user) throws Exception;

	void updateUser(User user) throws Exception;

	boolean checkPasswordStrength(String password) throws Exception;

	int login(User user) throws Exception;

	boolean registerWorker(User worker) throws Exception;

	boolean addManger(User newWorker, ArrayList<Integer> warehouseID) throws Exception;

	void getAccess(User user) throws Exception;

	boolean addWorkerToWh(User manager, int workerID, int warehouseID) throws Exception;

	boolean updWorkerFromWh(User manager,int workerID, int warehouseID) throws Exception;

	boolean delWorkerFromWh(User manager, int workerID) throws Exception;

	boolean delWorkerFromWh(User manager, int workerID, int warehouseID) throws Exception;
}
