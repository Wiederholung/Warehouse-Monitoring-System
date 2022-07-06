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
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("staff_password"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getString("telephone"));
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
	public int register(User user) {
		int flag = checkInfo(user);
		if (flag == 1) {
			DBConnector db = null;
			String sql = "insert into " +
					"staff(staff_id, username, staff_password, name, age, gender, telephone, is_manager) " +
					"values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst;

			try {
				db = new DBConnector();
				pst = db.getConnection().prepareStatement(sql);
				// 获取系统时间
				java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
				// 设置参数
				// TODO staff_id 自增，不需要设置
				pst.setInt(1, 4);
//				pst.setInt(1, Integer.parseInt(time.toString()));
				pst.setString(2, user.getUsername());
				pst.setString(3, user.getPassword());
				pst.setString(4, user.getName());
				pst.setInt(5, user.getAge());
				pst.setString(6, user.getGender());
				pst.setString(7, user.getPhone());
				int isManager = user.isManger() ? 1 : 0;
				pst.setInt(8, isManager);

				pst.executeUpdate();

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
}

