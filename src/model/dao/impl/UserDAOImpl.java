package model.dao.impl;

import model.dao.UserDAO;
import model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {
	@Override
	public boolean doseUserExist(User user) {
		boolean flag = false;
		DBConnector db = null;
		String sql = "select * from staff where username = ?";
		PreparedStatement pst;

		try {
			db = new DBConnector();
			pst = db.getConnection().prepareStatement(sql);
			pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();

			flag = rs.next();

			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Objects.requireNonNull(db).close();
		}
		return flag;
	}

	@Override
	public boolean checkPasswordStrength(User user) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int checkInfo(User user) {
		int flag;
		if (!doseUserExist(user)) {
			if (checkPasswordStrength(user)) flag = 1;
			else flag = -2;
		}
		else flag = -1;
		return flag;
	}

	@Override
	public int login(User user) {
		int flag = -1;
		DBConnector db = null;
		String sql = "select * from staff where username = ?";
		PreparedStatement pst;

		if (doseUserExist(user)) {
			// 下面是针对数据库的具体操作
			try {
				// 连接数据库
				db = new DBConnector();
				pst = db.getConnection().prepareStatement(sql);
				pst.setString(1, user.getUsername());

				// 进行数据库查询操作
				ResultSet rs = pst.executeQuery();
				System.out.println("User: " + user.getUsername()
						+ " || " + user.getPassword());

				// 只读第一行数据
				rs.next();
				System.out.println("DB: " + rs.getString("username")
						+ " || " + rs.getString("staff_password"));
				// 密码正确，就返回1，否则返回0
				flag = user.getPassword().equals(rs.getString("staff_password")) ? 1 : 0;

				// 数据库信息写入 user 对象中
				user.setUserID(rs.getInt("staff_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("staff_password"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getString("phone"));
				user.setGender(rs.getString("gender"));
				user.isManger(rs.getBoolean("is_manager"));

				// 关闭结果集和 statement
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} finally {
				// 关闭数据库连接
				Objects.requireNonNull(db).close();
			}
		}
		return flag;
	}

	@Override
	public int addManger(User manger, User newManager) {
		int flag = checkInfo(newManager);
		if (flag == 1) {
			DBConnector db = null;
			PreparedStatement pst;

			try {
				db = new DBConnector();
				String newStaff = "insert into " +
						"staff(username, staff_password, name, age, gender, phone, is_manager) " +
						"values(?, ?, ?, ?, ?, ?, ?)";
				pst = db.getConnection().prepareStatement(newStaff);
				// 创建新管理者
				pst.setString(1, newManager.getUsername());
				pst.setString(2, newManager.getPassword());
				pst.setString(3, newManager.getName());
				pst.setInt(4, newManager.getAge());
				pst.setString(5, newManager.getGender());
				pst.setString(6, newManager.getPhone());
				int isManager = manger.isManger() ? 1 : 0;
				pst.setInt(7, isManager);
				pst.executeUpdate();

				// 继承当前登录 manager 的权限
				String queryId = "select * from staff where username = ?";
				pst = db.getConnection().prepareStatement(queryId);
				pst.setString(1, newManager.getUsername());
				ResultSet rs = pst.executeQuery();
				rs.next();
				newManager.setUserID(rs.getInt("staff_id"));
				// 查询当前管理者的权限
				String queryAccess = "select * from staff_access where staff_staff_id = ?";
				pst = db.getConnection().prepareStatement(queryAccess);
				pst.setInt(1, manger.getUserID());
				rs = pst.executeQuery();
				// 循环添加新管理者的权限
				String newAccess = "insert into staff_access(staff_staff_id, warehouse_wh_id) values(?, ?)";
				pst = db.getConnection().prepareStatement(newAccess);
				pst.setInt(1, newManager.getUserID());
				while (rs.next()) {
					pst.setInt(2, rs.getInt("warehouse_wh_id"));
					pst.executeUpdate();
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} finally {
				// 关闭数据库连接
				Objects.requireNonNull(db).close();
			}
		}
		return flag;
	}

	@Override
	public boolean addWorker(User manger, User newWorker) {
		boolean flag = false;
		// 将管理者身份设置为工人身份
		manger.isManger(false);
		try	{
			flag = (addManger(manger, newWorker) == 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			// 恢复管理者身份
			manger.isManger(true);
		}
		return flag;
	}

	@Override
	public boolean delWorker(User worker) {
		return false;
	}
}

