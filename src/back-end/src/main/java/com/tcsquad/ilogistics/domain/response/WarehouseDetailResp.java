package com.tcsquad.ilogistics.domain.response;
import com.tcsquad.ilogistics.domain.storage.Category;

import java.util.*;

/**
 * 
 */
public class WarehouseDetailResp {
    private String warehouseId;
    private String mainSiteId;
    private Category category;
    private List<ItemInventoryResp> itemList = new ArrayList<ItemInventoryResp>();;
    private String mainsiteName;
    //private Map<String,Integer> inventory = new HashMap<String,Integer>();   //键为ItemId，值为库存数量

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

    public List<ItemInventoryResp> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemInventoryResp> itemList) {
        this.itemList = itemList;
    }

    public String getMainsiteName() {
        return mainsiteName;
    }

    public void setMainsiteName(String mainsiteName) {
        this.mainsiteName = mainsiteName;
    }
}