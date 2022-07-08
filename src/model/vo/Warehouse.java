package model.vo;

public class Warehouse {
    private int warehouseID;
    private String warehouseName;
    private String warehouseType;
    private int numShelf;
    private int numGood;
    private String createTime;

    public Warehouse() {
    }

    public Warehouse(String warehouseName, String warehouseType) {
        this.warehouseName = warehouseName;
        this.warehouseType = warehouseType;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }

    public int getNumShelf() {
        return numShelf;
    }

    public void setNumShelf(int numShelf) {
        this.numShelf = numShelf;
    }

    public int getNumGood() {
        return numGood;
    }

    public void setNumGood(int numGood) {
        this.numGood = numGood;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
