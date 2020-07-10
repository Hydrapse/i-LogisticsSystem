package com.tcsquad.ilogistics.domain.storage;

/**
 * 
 */
public class WarehouseResp {
    private String siteId;
    private String warehouseId;
    private Category category;
    private int kindsOfItem;
    private int totalSize;
    private int MaxSize;

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

    public int getKindsOfItem() {
        return kindsOfItem;
    }

    public void setKindsOfItem(int kindsOfItem) {
        this.kindsOfItem = kindsOfItem;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getMaxSize() {
        return MaxSize;
    }

    public void setMaxSize(int maxSize) {
        MaxSize = maxSize;
    }
}