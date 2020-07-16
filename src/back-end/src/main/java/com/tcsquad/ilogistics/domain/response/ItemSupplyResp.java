package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.storage.Item;

import java.util.List;

public class ItemSupplyResp {
    private String supplierId;
    private List<Item> itemSupplyList;//供应商品列表

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<Item> getItemSupplyList() {
        return itemSupplyList;
    }

    public void setItemSupplyList(List<Item> itemSupplyList) {
        this.itemSupplyList = itemSupplyList;
    }
}
