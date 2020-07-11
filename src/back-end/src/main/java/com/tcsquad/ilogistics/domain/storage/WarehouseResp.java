package com.tcsquad.ilogistics.domain.storage;

/**
 * 
 */
public class WarehouseResp {
    private String siteId;
    private String warehouseId;
    private Category category;
    private int kindNumOfItem;  //！！----修改
    private int totalSize;
    private int maxSize;

    /**
     * Default constructor
     */
    public WarehouseResp() {
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getKindNumOfItem() {
        return kindNumOfItem;
    }

    public void setKindNumOfItem(int kindNumOfItem) {
        this.kindNumOfItem = kindNumOfItem;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}