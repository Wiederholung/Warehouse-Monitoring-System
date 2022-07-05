package model.dao.impl;


import model.dao.UserDAO;
import model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

	public int queryByUsername(User user) {

		int flag = 0;
		String sql = "select * from staff where username = ?";
        PreparedStatement pstmt;
		DBConnector dbc = null;

		// 下面是针对数据库的具体操作
		try {
			// 连接数据库
			dbc = new DBConnector();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
//			pstmt.setString(1, "tom");

			// 进行数据库查询操作
			ResultSet rs = pstmt.executeQuery();
			System.out.println(user.getUsername() + " " + user.getPassword());
//			if (rs.wasNull()) {
//				flag = -1;
//				System.out.println("用户名不存在");
//			}
			while (rs.next()) {
				if (user.getPassword().equals(rs.getString("staff_password"))) {
					System.out.println(rs.getString("staff_password"));
					flag = 1;
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// 关闭数据库连接
			dbc.close();
		}
		return flag;
	}

	@Override
	public boolean checkUsername(String username) {
		return false;
	}
}

