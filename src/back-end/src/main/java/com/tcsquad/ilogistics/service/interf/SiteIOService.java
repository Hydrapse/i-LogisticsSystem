package com.tcsquad.ilogistics.service.interf;

import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.response.ItemCheckinResp;
import com.tcsquad.ilogistics.domain.response.ItemCheckoutResp;
import com.tcsquad.ilogistics.domain.response.SiteIOCheckInResp;
import com.tcsquad.ilogistics.domain.response.SiteIOCheckoutResp;
import com.tcsquad.ilogistics.domain.storage.SiteIO;

public interface SiteIOService {
    //新增出库记录: 其他模块调用该接口来生成出库请求
    void insertCheckOutRecord(String type, Long formId, String mainsiteId);

    //取消出入库记录（将status置为'F'）
    void cancelSiteIOStatus(Long recordId,boolean isCheckin);

    //出入库记录审核通过（将status置为'C'）
    void confirmSiteIORecord(Long recordId,boolean isCheckin);

    //新增入库记录
    Long insertCheckinRecord(SiteIOAddReq siteIOAddReq);

    //生成入库消息
    ItemCheckinResp getItemCheckinRespByRecordId(Long recordId);

    //查询入库单详细信息
    SiteIOCheckInResp getSiteIOCheckinRespByRecordId(Long recordId,String mainsiteId);

    //查询入库商品的来源
    String getItemCheckinSrc(Long formId,int type);

    //修改入库库房
    void updateWarehouseToCheckin(Long recordId,String warehouseId);

    //修改出库库房
    void updateWarehouseToCheckout(Long recordId,String warehouseId);

    //获取出库消息
    ItemCheckoutResp getItemCheckoutRespByRecordId(Long recordId);

    //查询出库单详细信息
    SiteIOCheckoutResp getSiteIOCheckoutRespByRecordId(Long recordId, String mainsiteId);

    //查询出库商品的出处
    String getItemCheckoutDest(Long formId,int type);

    //发送入库消息
    void sendItemCheckinMessage(ItemCheckinResp itemCheckinResp);

    //发送出库请求
    void sendItemCheckoutMessage(ItemCheckoutResp itemCheckoutResp);

    //入库是否需要审核
    boolean isCheckNeeded_In(SiteIOAddReq siteIOAddReq);

    boolean isCheckNeeded_Out(ItemCheckoutResp itemCheckoutResp);
}
