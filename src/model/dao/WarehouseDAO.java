package model.dao;

import model.vo.Warehouse;

import java.util.List;

public interface WarehouseDAO {
    boolean addWarehouse(Warehouse warehouse);
    List<Warehouse> queryWarehouse(Warehouse warehouse);
    List<Warehouse> queryWarehouse();
    boolean updateWarehouse(Warehouse warehouse);
    boolean delWarehouse(Warehouse warehouse);
}
