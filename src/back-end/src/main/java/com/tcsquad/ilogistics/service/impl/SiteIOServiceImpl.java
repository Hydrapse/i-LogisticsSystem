package com.tcsquad.ilogistics.service.impl;

import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.ReturnForm;
import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.response.ItemCheckinResp;
import com.tcsquad.ilogistics.domain.response.ItemCheckoutResp;
import com.tcsquad.ilogistics.domain.response.SiteIOCheckInResp;
import com.tcsquad.ilogistics.domain.response.SiteIOCheckoutResp;
import com.tcsquad.ilogistics.domain.storage.AdjustForm;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.domain.storage.SiteIO;
import com.tcsquad.ilogistics.mapper.clientele.SupplyIOMapper;
import com.tcsquad.ilogistics.mapper.order.OrderMapper;
import com.tcsquad.ilogistics.mapper.order.ReturnFormMapper;
import com.tcsquad.ilogistics.mapper.storage.AdjustFormMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteIOMapper;
import com.tcsquad.ilogistics.mapper.storage.WarehouseMapper;
import com.tcsquad.ilogistics.service.SiteIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SiteIOServiceImpl implements SiteIOService {
    @Autowired
    SiteIOMapper siteIOMapper;

    @Autowired
    WarehouseMapper warehouseMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    SupplyIOMapper supplyIOMapper;

    @Autowired
    AdjustFormMapper adjustFormMapper;

    @Autowired
    ReturnFormMapper returnFormMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public void cancelSiteIOStatus(Long recordId) {
        //Todo:取消入库对其他模块的影响？？？
        siteIOMapper.updateSiteIOStatus(recordId, StatusString.INVALID.getValue());
    }

    @Override
    public void confirmSiteIORecord(Long recordId) {
        //Todo:确认入库对其他模块的影响？？？
        siteIOMapper.updateSiteIOStatus(recordId,StatusString.CONFIRM.getValue());
    }

    //1表示补货，2表示调货，3表示退货，4表示换货
    @Override
    public Long insertCheckinRecord(SiteIOAddReq siteIOAddReq) {
        SiteIO siteIO = new SiteIO();
        String typeValue = "";
        switch (siteIOAddReq.getType()){
            case 1:
                typeValue = StatusString.SUPPLY_IN.getValue();break;
            case 2:
                typeValue = StatusString.ADJUST_IN.getValue();break;
            case 3:
                typeValue = StatusString.RETURN_IN.getValue();break;
            case 4:
                typeValue = StatusString.RETURN_IN.getValue();
            default:
                System.out.println("type值不合法");
        }

        if(typeValue != ""){
            Date currentDate = new Date();
            siteIO.setTimeStamp(currentDate);
            siteIO.setWarehouseId(siteIOAddReq.getWarehouseId());
            siteIO.setItemId(siteIOAddReq.getItemId());
            siteIO.setQty(siteIOAddReq.getItemNum());
            siteIO.setType(typeValue);
            siteIO.setFormId(siteIOAddReq.getFormId());
            siteIO.setApprovalStatus(StatusString.WAITING.getValue());
            siteIO.setApprover("Auto");                //"Auto"表示为程序根据请求自动出入库
            siteIOMapper.insertSiteIORecord(siteIO);

            //查询本次入库请求的recordId
            Long recordId = siteIOMapper.getSiteIORecordIdByFormIdAndItemId(siteIO.getType(),siteIO.getFormId(),siteIO.getItemId());
            return recordId;
        }
        else {
            //出错
            //Todo: Error
        }

        return null;

    }

    @Override
    public ItemCheckinResp getItemCheckinRespByRecordId(Long recordId) {
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        ItemCheckinResp itemCheckinResp = new ItemCheckinResp();
        itemCheckinResp.setType(checkinTypeStringToInteger(siteIO.getType()));
        itemCheckinResp.setFormId(siteIO.getFormId());
        itemCheckinResp.setItemId(siteIO.getItemId());
        itemCheckinResp.setItemNum(siteIO.getQty());
        itemCheckinResp.setRecordId(siteIO.getRecordId());
        String typeDesc = getCheckinDesc(itemCheckinResp.getType());
        if(typeDesc == ""){
            //Todo:Error
        }
        else {
            itemCheckinResp.setTypeDesc(typeDesc);
        }
        return itemCheckinResp;
    }

    @Override
    public SiteIOCheckInResp getSiteIOCheckinRespByRecordId(Long recordId,String mainsiteId) {
        SiteIOCheckInResp checkInResp = new SiteIOCheckInResp();
        checkInResp.setRecordId(recordId);
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        checkInResp.setTimeStamp(siteIO.getTimeStamp());
        checkInResp.setWarehouseId(siteIO.getWarehouseId());
        Item item = itemMapper.getItem(siteIO.getItemId());
        checkInResp.setItem(item);
        checkInResp.setItemNum(siteIO.getQty());
        checkInResp.setType(checkinTypeStringToInteger(siteIO.getType()));
        String typeDesc = getCheckinDesc(checkInResp.getType());
        if(typeDesc == ""){
            //Todo:Error
        }
        else {
            checkInResp.setTypeDesc(typeDesc);
        }
        checkInResp.setFormId(siteIO.getFormId());
        checkInResp.setItemSrc(getItemCheckinSrc(checkInResp.getFormId(),checkInResp.getType()));
        checkInResp.setApprovalStatus(siteIO.getApprovalStatus());
        checkInResp.setApprover(siteIO.getApprover());
        List<String> warehousesOptional = warehouseMapper.getWarehouseOptionsToCheckin(item.getItemId(),siteIO.getQty(),mainsiteId);
        checkInResp.setWarehouseOptionalList(warehousesOptional);
        return checkInResp;
    }

    @Override
    public String getItemCheckinSrc(Long formId, int type) {
        if(type == 1){
            Supplier supplier = supplyIOMapper.getSupplyByRecordId(formId);
            return supplier.getBrandName();
        }
        else if(type == 2){
            AdjustForm adjustForm = adjustFormMapper.getAdjustForm(formId);
            return adjustForm.getToMainSiteId();
        }
        else if(type == 3 | type == 4){
            ReturnForm returnForm = returnFormMapper.getReturnFormByFormId(formId);
            Order order = orderMapper.getOrder(returnForm.getOrderId());
            return order.getCustomerId();
        }
        else
            return null;
    }

    @Override
    public void updateWarehouseToCheckin(Long recordId, String warehouseId) {
        siteIOMapper.updateSiteIOWarehouseId(recordId,warehouseId);
    }


    @Override
    public void insertCheckOutRecord(SiteIO siteIO,String mainsiteId) {
        siteIO.setApprovalStatus(StatusString.WAITING.getValue());
        siteIO.setTimeStamp(new Date());
        siteIO.setApprover("Auto");
        List<String> warehouseOptions = warehouseMapper.getWarehouseOptionsToCheckout(siteIO.getItemId(),siteIO.getQty(),mainsiteId);

        //Todo:这里应该需要增加一些策略
        //此处默认选第一个
        siteIO.setWarehouseId(warehouseOptions.get(0));
        siteIOMapper.insertSiteIORecord(siteIO);

        //Todo:减少库存
    }

    @Override
    public ItemCheckoutResp getItemCheckoutRespByRecordId(Long recordId) {
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        ItemCheckoutResp itemCheckoutResp = new ItemCheckoutResp();
        itemCheckoutResp.setType(checkoutTypeStringToInteger(siteIO.getType()));
        itemCheckoutResp.setFormId(siteIO.getFormId());
        itemCheckoutResp.setItemId(siteIO.getItemId());
        itemCheckoutResp.setItemNum(siteIO.getQty());
        itemCheckoutResp.setRecordId(siteIO.getRecordId());
        String typeDesc = getCheckoutDesc(itemCheckoutResp.getType());
        if(typeDesc == ""){
            //Todo:Error
        }
        else {
            itemCheckoutResp.setTypeDesc(typeDesc);
        }
        return itemCheckoutResp;
    }

    @Override
    public SiteIOCheckoutResp getSiteIOCheckoutRespByRecordId(Long recordId, String mainsiteId) {
        SiteIOCheckoutResp checkoutResp = new SiteIOCheckoutResp();
        checkoutResp.setRecordId(recordId);
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        checkoutResp.setTimeStamp(siteIO.getTimeStamp());
        checkoutResp.setWarehouseId(siteIO.getWarehouseId());
        Item item = itemMapper.getItem(siteIO.getItemId());
        checkoutResp.setItem(item);
        checkoutResp.setItemNum(siteIO.getQty());
        checkoutResp.setType(checkoutTypeStringToInteger(siteIO.getType()));
        String typeDesc = getCheckoutDesc(checkoutResp.getType());
        if(typeDesc == ""){
            //Todo:Error
        }
        else {
            checkoutResp.setTypeDesc(typeDesc);
        }
        checkoutResp.setFormId(siteIO.getFormId());
        checkoutResp.setItemDest(getItemCheckoutDest(checkoutResp.getFormId(),checkoutResp.getType()));
        checkoutResp.setApprovalStatus(siteIO.getApprovalStatus());
        checkoutResp.setApprover(siteIO.getApprover());
        List<String> warehousesOptional = warehouseMapper.getWarehouseOptionsToCheckout(item.getItemId(),siteIO.getQty(),mainsiteId);
        checkoutResp.setWarehouseOptionalList(warehousesOptional);
        return checkoutResp;

    }

    @Override
    public String getItemCheckoutDest(Long formId, int type) {
        if(type == 1){
            Supplier supplier = supplyIOMapper.getSupplyByRecordId(formId);
            return supplier.getBrandName();
        }
        else if(type == 2){
            //调货给其他主站
            AdjustForm adjustForm = adjustFormMapper.getAdjustForm(formId);
            return adjustForm.getFromMainSiteId();
        }
        else if(type == 3){
            //发货给用户
            Order order = orderMapper.getOrder(formId);
            return order.getCustomerId();

        }
        return null;
    }

    int checkinTypeStringToInteger(String typeString){
        if(typeString == StatusString.SUPPLY_IN.getValue()){
            return 1;
        }
        else if(typeString == StatusString.ADJUST_IN.getValue()){
            return 2;
        }
        else if(typeString == StatusString.RETURN_IN.getValue()){
            return 3;
        }
        else if(typeString == StatusString.CHANGE_IN.getValue()){
            return 4;
        }
        else {
            //Todo:报错

            return -1;
        }
    }

    //1：退货给供应商，2：调货出库，3：发货出库
    int checkoutTypeStringToInteger(String typeString){
        if(typeString == StatusString.SUPPLY_OUT.getValue()){
            return 1;
        }
        else if(typeString == StatusString.ADJUST_OUT.getValue()){
            return 2;
        }
        else if(typeString == StatusString.SHIP_OUT.getValue()){
            return 3;
        }
        else {
            //Todo:报错

            return -1;
        }
    }

    String getCheckinDesc(int type){
        String typeDesc = "";
        switch (type){
            case 1:
                typeDesc = "补货";break;
            case 2:
                typeDesc = "调货";break;
            case 3:
                typeDesc = "用户退货";break;
            case 4:
                typeDesc = "换货";
            default:
                typeDesc = "";
                //Todo: Error
        }

        return typeDesc;
    }

    String getCheckoutDesc(int type){
        String typeDesc = "";
        switch (type){
            case 1:
                typeDesc = "主站退货";break;
            case 2:
                typeDesc = "调货";break;
            case 3:
                typeDesc = "发货";
            default:
                typeDesc = "";
                //Todo: Error
        }

        return typeDesc;
    }

}
