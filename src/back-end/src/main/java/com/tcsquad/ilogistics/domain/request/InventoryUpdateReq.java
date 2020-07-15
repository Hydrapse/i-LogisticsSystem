package com.tcsquad.ilogistics.domain.request;

import lombok.Data;

@Data
public class InventoryUpdateReq {
    String mainsiteId;
    String itemId;
    String sourceWarehouseId;
    String destWarehouseId;
    Integer itemNum;
}
