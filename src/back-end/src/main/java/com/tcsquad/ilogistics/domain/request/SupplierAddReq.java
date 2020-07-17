package com.tcsquad.ilogistics.domain.request;

import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.storage.Item;

import java.util.List;

public class SupplierAddReq {
    private Supplier supplier;
    private List<String> itemSupplyList;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<String> getItemSupplyList() {
        return itemSupplyList;
    }

    public void setItemSupplyList(List<String> itemSupplyList) {
        this.itemSupplyList = itemSupplyList;
    }
}
