package model.dao.impl;

import model.dao.WarehouseDAO;
import model.vo.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarehouseDAOImpl implements WarehouseDAO {
    public boolean addWarehouse(Warehouse warehouse) {
        // TODO 检测仓库是否重名
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
        List<Warehouse> qList = queryWarehouse();
        List<Warehouse> whList = new ArrayList<>();
        // 找出仓库名为warehouse.getWarehouseName()的仓库
        for (Warehouse wh : qList) {
            if (wh.getWarehouseName().equals(warehouse.getWarehouseName())) {
                whList.add(wh);
                break;
            }
        }
        return whList;
    }

    public List<Warehouse> queryWarehouse() {
        List<Warehouse> whList = new ArrayList<>();
        DBConnector db = null;
        String sql = "select * from warehouse";

        try {
            db = new DBConnector();
            ResultSet rs = db.getStatement().executeQuery(sql);

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouseID(rs.getInt("wh_id"));
                warehouse.setWarehouseName(rs.getString("wh_name"));
                warehouse.setWarehouseType(rs.getString("wh_type"));
                warehouse.setNumShelf(rs.getInt("num_shelf"));
                warehouse.setNumGood(rs.getInt("num_good"));
                warehouse.setCreateTime(rs.getTimestamp("create_time").toString());

                whList.add(warehouse);
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(db).close();
        }
        return whList;
    }

    public boolean updateWarehouse(Warehouse warehouse) {
        // TODO 检测仓库是否存在
        return addWarehouse(warehouse);
    }

    public boolean delWarehouse(Warehouse warehouse) {
        // TODO 约束：同时删除仓库下的货架/货物，以及管理者和工人的访问权限
        return false;
    }
}