package model.dao.impl;

import model.dao.UserDAO;
import model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {
	@Override
	public User getUser(String username) throws Exception {
		User user = new User();
		DBConnector db = null;
		String sql = "select * from staff where username = '"+ username +"'";
		try {
			db = new DBConnector();
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				// 数据库信息写入 user 对象中
				user.setUserID(rs.getInt("staff_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("staff_password"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getString("phone"));
				user.setGender(rs.getString("gender"));
				user.isManger(rs.getBoolean("is_manager"));

				getAccess(user);
			}
			else user = null;
			// 关闭结果集
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {
			Objects.requireNonNull(db).close();
		}
		return user;
	}

	@Override
	public void addUser(User user) throws Exception {
		DBConnector db = null;
		String newStaff = "insert into " +
				"staff(username, staff_password, name, age, gender, phone, is_manager) " +
				"values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst;
		try {
			db = new DBConnector();
			if (checkPasswordStrength(user.getPassword())) {
				pst = db.getConnection().prepareStatement(newStaff);
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getName());
				pst.setInt(4, user.getAge());
				pst.setString(5, user.getGender());
				pst.setString(6, user.getPhone());
				int isManager = user.isManger() ? 1 : 0;
				pst.setInt(7, isManager);
				pst.executeUpdate();
				pst.close();
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		} finally {
			Objects.requireNonNull(db).close();
		}
	}

	@Override
	// TODO 更新
	public void updateUser(User user) throws Exception {
		addUser(user);
	}


	@Override
	public boolean checkPasswordStrength(String password) throws Exception {
		boolean flag = false;
		try	{
			if (password.length() >= 6) flag = true;
			else System.out.println("Weak password");
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public int login(User user) throws Exception {
		int flag;
		User tUser = getUser(user.getUsername());
		if (tUser != null) {
			if (tUser.getPassword().equals(user.getPassword())) {
				user.setUserID(tUser.getUserID());
				user.setName(tUser.getName());
				user.setAge(tUser.getAge());
				user.setPhone(tUser.getPhone());
				user.setGender(tUser.getGender());
				user.isManger(tUser.isManger());
				user.setHasWarehouse(tUser.getHasWarehouse());
				flag = 1;
				System.out.println("You are in!");
			}
			else {
				flag = 0;
				System.out.println("Wrong password.");
			}
		}
		else {
			flag = -1;
			System.out.println("Not a user.");
		}
		return flag;
	}

	@Override
	public boolean registerWorker(User worker) throws Exception {
		boolean flag;
		try {
			worker.isManger(false);
			addUser(worker);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean addManger(User newManager, ArrayList<Integer> warehouseID) throws Exception {
		boolean flag;
		newManager.isManger(true);
		try {
			newManager.setHasWarehouse(warehouseID);
			addUser(newManager);
			User temp = getUser(newManager.getUsername());
			newManager.setUserID(temp.getUserID());
			for (int whID : newManager.getHasWarehouse()) {
				addWorkerToWh(newManager, newManager.getUserID(), whID);
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}
//		int flag = 0;
//		if (flag == 1) {
//			// 添加新管理者
//			// 添加权限
//			User temp = getUser(newManager.getUsername());
//			temp.isManger(manger.isManger());
//
//			DBConnector db = null;
//			PreparedStatement pst;
//			try {
//				db = new DBConnector();
//				String newStaff = "insert into " +
//						"staff(username, staff_password, name, age, gender, phone, is_manager) " +
//						"values(?, ?, ?, ?, ?, ?, ?)";
//				pst = db.getConnection().prepareStatement(newStaff);
//				// 创建新管理者
//				pst.setString(1, newManager.getUsername());
//				pst.setString(2, newManager.getPassword());
//				pst.setString(3, newManager.getName());
//				pst.setInt(4, newManager.getAge());
//				pst.setString(5, newManager.getGender());
//				pst.setString(6, newManager.getPhone());
//				int isManager = manger.isManger() ? 1 : 0;
//				pst.setInt(7, isManager);
//				pst.executeUpdate();
//
//				// 继承当前登录 manager 的权限
//				User tUser = getUser(newManager.getUsername());
//				newManager.setUserID(tUser.getUserID());
//				// 查询当前管理者的权限
//				String queryAccess = "select * from staff_access where staff_staff_id = ?";
//				pst = db.getConnection().prepareStatement(queryAccess);
//				pst.setInt(1, manger.getUserID());
//				ResultSet rs = pst.executeQuery();
//				// 循环添加新管理者的权限
//				String newAccess = "insert into staff_access(staff_staff_id, warehouse_wh_id) values(?, ?)";
//				pst = db.getConnection().prepareStatement(newAccess);
//				pst.setInt(1, newManager.getUserID());
//				while (rs.next()) {
//					pst.setInt(2, rs.getInt("warehouse_wh_id"));
//					pst.executeUpdate();
//				}
//				rs.close();
//				pst.close();
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			} finally {
//				// 关闭数据库连接
//				Objects.requireNonNull(db).close();
//			}
//		}
//		return flag;
//	}

	@Override
	public void getAccess(User user) throws Exception {
		DBConnector db = null;
		String sql = "select * from staff_access where staff_staff_id = '"+ user.getUserID() +"'";
		try {
			db = new DBConnector();
			ResultSet rs = db.getStatement().executeQuery(sql);
			ArrayList<Integer> whID = new ArrayList<>();
			while (rs.next()) { whID.add(rs.getInt("warehouse_wh_id")); }
			user.setHasWarehouse(whID);
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {
			Objects.requireNonNull(db).close();
		}
	}

	@Override
	public boolean addWorkerToWh(User manager, int workerID, int warehouseID) throws Exception{
		boolean flag;
		int count = manager.getHasWarehouse().size();
		DBConnector db = null;
		String newAccess = "insert into staff_access(staff_staff_id, warehouse_wh_id) " +
				"values (?, ?)";
		PreparedStatement pst;
		try {
			db = new DBConnector();
			// 写入 staff_access 表
			pst = db.getConnection().prepareStatement(newAccess);
			pst.setInt(1, workerID);
			for (int access : manager.getHasWarehouse()) {
				if (access == warehouseID) {
					pst.setInt(2, warehouseID);
					pst.executeUpdate();
					System.out.println("The manager has access " +
							"to the warehouse: " + warehouseID);
				}
				else {
					count--;
				}
			}
			pst.close();
			if (count == 0) {
				flag = false;
				System.out.println("The manager has no access to warehouse: " + warehouseID);
			}
			else flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			Objects.requireNonNull(db).close();
		}
		return flag;
	}

	@Override
	// TODO 更新
	public boolean updWorkerFromWh(User manager, int workerID, int warehouseID) throws Exception{
		boolean flag;
		try {
			addWorkerToWh(manager, workerID, warehouseID);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean delWorkerFromWh(User manager, int workerID) throws Exception{
		boolean flag;
		DBConnector db = null;
		String sql = "delete from staff_access where staff_staff_id = ? and warehouse_wh_id = ?";
		PreparedStatement pst;
		try {
			db = new DBConnector();
			// 删除 staff_access 表中的记录
			pst = db.getConnection().prepareStatement(sql);
			pst.setInt(1, workerID);
			for (int whID : manager.getHasWarehouse()) {
				pst.setInt(2, whID);
				pst.executeUpdate();
				System.out.println("The manager has deleted " +
						"the access to the warehouse: " + whID);
			}
			pst.close();
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			Objects.requireNonNull(db).close();
		}
		return flag;
	}

	@Override
	public boolean delWorkerFromWh(User manager, int workerID, int warehouseID) throws Exception{
		boolean flag;
		int count = manager.getHasWarehouse().size();
		DBConnector db = null;
		String sql = "delete from staff_access where staff_staff_id = ? and warehouse_wh_id = ?";
		PreparedStatement pst;
		try {
			db = new DBConnector();
			// 删除 staff_access 表中的记录
			pst = db.getConnection().prepareStatement(sql);
			pst.setInt(1, workerID);
			pst.setInt(2, warehouseID);
			for (int whID : manager.getHasWarehouse()) {
				if (whID == warehouseID) {
					pst.executeUpdate();
					System.out.println("The manager has deleted " +
							"the access to the warehouse: " + whID);
				}
				else { count--; }
				if (count == 0) {
					flag = false;
					System.out.println("The manager has no access " +
							"to the warehouse: " + warehouseID);
				}
			}
			pst.close();
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			Objects.requireNonNull(db).close();
		}
		return flag;
	}

}