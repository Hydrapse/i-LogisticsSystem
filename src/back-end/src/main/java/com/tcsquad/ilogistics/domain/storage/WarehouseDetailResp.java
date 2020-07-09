package com.tcsquad.ilogistics.domain.storage;
import java.util.*;

/**
 * 
 */
public class WarehouseDetailResp {
    private String warehouseId;
    private String mainSiteId;
    private Category category;
    private List<Item> itemList = new ArrayList<Item>();;
    private String mainSiteName;
    private Map<String,Integer> inventory = new HashMap<String,Integer>();

    /**
     * Default constructor
     */
    public WarehouseDetailResp() {
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getMainSiteId() {
        return mainSiteId;
    }

    public void setMainSiteId(String mainSiteId) {
        this.mainSiteId = mainSiteId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getMainSiteName() {
        return mainSiteName;
    }

    public void setMainSiteName(String mainSiteName) {
        this.mainSiteName = mainSiteName;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }
}