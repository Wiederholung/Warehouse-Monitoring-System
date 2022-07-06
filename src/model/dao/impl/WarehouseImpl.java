package model.dao.impl;


import model.dao.WarehouseDAO;
import model.vo.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class WarehouseImpl implements WarehouseDAO {
    public boolean addWarehouse(Warehouse warehouse) {
        boolean flag;
        DBConnector db = null;
        String sql = "insert into " +
                "warehouse(wh_name, wh_type, num_shelf) " +
                "values(?, ?, ?)";
        PreparedStatement pst;

        try {
            db = new DBConnector();
            pst = db.getConnection().prepareStatement(sql);

            // 设置参数
            // TODO 检查仓库是否重名
            pst.setString(1, warehouse.getWarehouseName());
            pst.setString(2, warehouse.getWarehouseType());
            pst.setInt(3, warehouse.getNumShelf());

            pst.executeUpdate();

            pst.close();
            // TODO 将仓库授权给管理员/工人
            flag = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            flag = false;
        } finally {
            // 关闭数据库连接
            Objects.requireNonNull(db).close();
        }
        return flag;
    }

    public List<Warehouse> queryWarehouse(Warehouse warehouse) {
        List<Warehouse> whList = null;
        DBConnector db = null;
        String sql = "select * from warehouse where wh_name = ?";
        PreparedStatement pst;
        try {
            db = new DBConnector();
            pst = db.getConnection().prepareStatement(sql);
            pst.setString(1, warehouse.getWarehouseName());
            ResultSet rs = pst.executeQuery();
            warehouse.setWarehouseID(rs.getInt("wh_id"));
            warehouse.setWarehouseName(rs.getString("wh_name"));
            warehouse.setWarehouseType(rs.getString("wh_type"));
            warehouse.setNumShelf(rs.getInt("num_shelf"));
            warehouse.setNumGood(rs.getInt("num_good"));
            warehouse.setCreateTime(rs.getTimestamp("create_time").toString());

            rs.close();
            pst.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(db).close();
        }
        return whList;
    }

    public List<Warehouse> queryWarehouse() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean updateWarehouse(Warehouse warehouse) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean delWarehouse(Warehouse warehouse) {
        // TODO Auto-generated method stub
        return false;
    }

}
