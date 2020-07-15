package com.tcsquad.ilogistics.domain.storage;

import lombok.Data;

@Data
public class Inventory {
    private String warehouseId;
    private String itemId;
    Integer itemNum;
    Integer logicInventory;
}
