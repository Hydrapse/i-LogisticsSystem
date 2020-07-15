package com.tcsquad.ilogistics.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class ItemInventoryGetReq {
    String mainsiteId;
    String keyword;
    String[] warehouseIdList;
    String[] categoryIdList;
}
