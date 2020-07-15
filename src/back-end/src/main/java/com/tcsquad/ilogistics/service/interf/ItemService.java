package com.tcsquad.ilogistics.service.interf;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.ItemInventoryGetReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.ItemInventoryResp;

import java.util.List;

public interface ItemService {
    //根据请求来获取库房货物列表
    PageResult getItemInventoryRespByRequest(ItemInventoryGetReq req, PageRequest pageReq);

}
