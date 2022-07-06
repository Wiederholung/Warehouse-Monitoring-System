package model.dao;

import model.vo.Warehouse;

import java.util.List;

public interface WarehouseDAO {
    public boolean addWarehouse(Warehouse warehouse);
    public List<Warehouse> queryWarehouse(Warehouse warehouse);
    public List<Warehouse> queryWarehouse();
    public boolean updateWarehouse(Warehouse warehouse);
    public boolean delWarehouse(Warehouse warehouse);
}
