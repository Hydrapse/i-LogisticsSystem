package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.storage.Item;

public class ItemInventoryResp{
    Item item;
    Integer inventory;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
