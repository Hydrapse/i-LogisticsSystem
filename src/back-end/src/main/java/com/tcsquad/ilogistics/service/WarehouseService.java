package com.tcsquad.ilogistics.service;

import java.util.List;

public interface WarehouseService {

    List<String> getWarehouseIdsByItemAndMainsite(String itemId, String mainsiteId);
}
