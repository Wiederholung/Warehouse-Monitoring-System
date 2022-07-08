package model.dao;

import model.vo.Warehouse;

import java.util.List;

public interface WarehouseDAO {
    int getWarehouseID(Warehouse warehouse);
    boolean addWarehouse(Warehouse warehouse) throws Exception;
    List<Warehouse> queryWarehouse(Warehouse warehouse);
    List<Warehouse> queryWarehouse();
    boolean updateWarehouse(Warehouse warehouse) throws Exception;
    boolean delWarehouse(Warehouse warehouse);
}
