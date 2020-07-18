package com.tcsquad.ilogistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.clientele.SupplyIO;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.order.ReturnForm;
import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.response.ItemCheckinResp;
import com.tcsquad.ilogistics.domain.response.ItemCheckoutResp;
import com.tcsquad.ilogistics.domain.response.SiteIOCheckInResp;
import com.tcsquad.ilogistics.domain.response.SiteIOCheckoutResp;
import com.tcsquad.ilogistics.domain.storage.*;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.mapper.clientele.SupplyIOMapper;
import com.tcsquad.ilogistics.mapper.order.OrderMapper;
import com.tcsquad.ilogistics.mapper.order.ReturnFormMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.*;
import com.tcsquad.ilogistics.service.LogicalInventoryService;
import com.tcsquad.ilogistics.service.OrderService;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import com.tcsquad.ilogistics.service.interf.WarehouseService;
import com.tcsquad.ilogistics.util.IDSequenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SiteIOServiceImpl implements SiteIOService {
    private static Logger logger = LoggerFactory.getLogger(SiteIOServiceImpl.class);

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
    @Autowired
    TaskFormMapper taskFormMapper;
    @Autowired
    LogicInventoryMapper logicInventoryMapper;
    @Autowired
    SiteMapper siteMapper;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    LogicalInventoryService logicalInventoryService;
    @Autowired
    AmqpTemplate amqpTemplate;
    @Autowired
    IDSequenceUtil idSequenceUtil;

    @Override
    @Transactional
    public void cancelSiteIOStatus(Long recordId,boolean isCheckin) {
        if(isCheckin){
            logger.info("操作员取消入库单" + recordId + "中的商品入库");
            //Todo:取消入库对其他模块的影响(类似无影响)
        }
        else {
            logger.info("操作员取消出库单" + recordId + "中的商品出库");
            //取消出库,则将逻辑库存加上itemNum
            SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
            Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(siteIO.getWarehouseId(),siteIO.getItemId());
            int num = inventory.getLogicInventory() + siteIO.getQty();
            inventory.setLogicInventory(num);
            warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);

            //取消出库对其他模块的影响
            if(siteIO.getType().equals(StatusString.SHIP_OUT.getValue())){
                //Todo:让任务单处理中心重新进行调度
            }
            else if(siteIO.getType().equals(StatusString.ADJUST_OUT.getValue())){
                //TODO: 调货单重新调度
            }
            else if(siteIO.getType().equals(StatusString.SUPPLY_OUT.getValue())){
                //Todo: 改变供应商退订记录的状态
            }

            //TODO: 取消时 更新逻辑库存, 待定
//            MainSite mainSite = siteMapper.getMainsiteByWarehouseId(siteIO.getWarehouseId());
//            logicalInventoryService.handleIncrease(mainSite.getMainsiteId(), siteIO.getItemId(), siteIO.getQty());
        }

        siteIOMapper.updateSiteIOStatus(recordId, StatusString.INVALID.getValue());

    }

    /**
     * 功能描述:<br>
     * 确认出入库单记录 重要!!
     */
    @Override
    @Transactional
    public void confirmSiteIORecord(Long recordId,boolean isCheckin) {
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        if(isCheckin){
            logger.info("操作员确认入库单" + recordId + "中的商品入库");
            //更新出入库单
            siteIOMapper.updateSiteIOStatus(recordId,StatusString.CONFIRM.getValue());

            //更新真实库存
            warehouseService.addItemToWarehouse(siteIO.getWarehouseId(),siteIO.getItemId(),siteIO.getQty());

            //更新逻辑库存, 处理缺货订单
            MainSite mainSite = siteMapper.getMainsiteByWarehouseId(siteIO.getWarehouseId());
            logger.info("更新逻辑库存, 会自动检查缺货消息,并处理缺货订单");
            logicalInventoryService.handleIncrease(mainSite.getMainsiteId(), siteIO.getItemId(), siteIO.getQty());

            //确认入库对其他模块的影响
            if(siteIO.getType().equals(StatusString.SUPPLY_IN.getValue())){
                //TODO: 改变供应商退订记录的状态，并向供应商发送已收到货的邮箱

            }
            else if(siteIO.getType().equals(StatusString.ADJUST_IN.getValue())){
                //改变调货单的状态
                AdjustForm preAdjustForm = adjustFormMapper.getAdjustForm(siteIO.getFormId());
                preAdjustForm.setAdjustStatus(StatusString.A_REACH.getValue());
                adjustFormMapper.updateAdjustFormStatus(preAdjustForm);
            }
            else if(siteIO.getType().equals(StatusString.RETURN_IN)){
                //改变退货单记录的状态
                returnFormMapper.updateReturnFormStatus(StatusString.R_SUCCESS.getValue(),siteIO.getFormId());
                //Todo:发送退货成功的消息给上游系统
            }

        }
        else {
            logger.info("操作员确认出库单" + recordId + "中的商品出库");
            //确认出库，将真实库存减掉itemNum
            Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(siteIO.getWarehouseId(),siteIO.getItemId());
            int num = inventory.getItemNum() - siteIO.getQty();
            if(num >= 0){
                inventory.setItemNum(num);
                warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);
                siteIOMapper.updateSiteIOStatus(recordId,StatusString.CONFIRM.getValue());
            }
            else {
                throw new BusinessErrorException("业务逻辑异常, 库房中该商品库存不足，无法出库",
                        ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
            }

            //Todo:确认出库对其他模块的影响
            if(siteIO.getType().equals(StatusString.SHIP_OUT.getValue())){
                //--修改任务单状态
                taskFormMapper.updateTaskFormStatus(StatusString.T_ON_THE_WAY.getValue(),siteIO.getFormId());
            }
            else if(siteIO.getType().equals(StatusString.ADJUST_OUT.getValue())){
                //--改变调货单的状态,改成已处理
                AdjustForm preAdjustForm = adjustFormMapper.getAdjustForm(siteIO.getFormId());
                preAdjustForm.setAdjustStatus(StatusString.A_PROCESSED.getValue());
                adjustFormMapper.updateAdjustFormStatus(preAdjustForm);
            }
            else if(siteIO.getType().equals(StatusString.SUPPLY_OUT.getValue())){
                //Todo: 改变供应商退订记录的状态
                //Todo: 向供应商发送已到货邮件
            }
        }

    }

    //1表示补货，2表示调货，3表示退货，4表示换货
    @Override
    @Transactional
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

            Long nextId = idSequenceUtil.getNextFormIdByName(SequenceName.MAINSITEIO_FORM.getValue());
            siteIO.setRecordId(nextId);
            siteIOMapper.insertSiteIORecord(siteIO);
            logger.info("生成入库记录，并保存到数据库，入库单编号为"+ nextId);

            //查询本次入库请求的recordId
            //Long recordId = siteIOMapper.getSiteIORecordIdByFormIdAndItemId(siteIO.getType(),siteIO.getFormId(),siteIO.getItemId());
            return nextId;
        }
        else {
            //出错
            //Todo: Error
        }

        return null;

    }

    @Override
    public void sendItemCheckinMessage(ItemCheckinResp itemCheckinResp) {
        JSONObject msg = new JSONObject();

        msg.put("type", itemCheckinResp.getType());
        msg.put("typeDesc", itemCheckinResp.getTypeDesc());
        msg.put("formId", itemCheckinResp.getFormId());
        msg.put("itemId", itemCheckinResp.getItemId());
        msg.put("itemNum",itemCheckinResp.getItemNum());
        msg.put("recordId",itemCheckinResp.getRecordId());

        //向待审核入库消息队列发送消息unreviewed item in
//        amqpTemplate.convertAndSend("unreviewed order", orderMsg.toJSONString());
        amqpTemplate.convertAndSend("unreviewed item in", msg.toJSONString());

        logger.info("成功发送入库消息: " + msg.toJSONString());
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
        logger.info("获取入库单" + recordId + "的入库消息");
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

        //验证入库库房是否与查询所得相一致，避免提供的入库编号并不是该主站的记录，同时将入库库房作为可供入库选择的第一个元素
        int index = 0;
        for (String warehouseId:warehousesOptional
             ) {
            if(warehouseId.equals(checkInResp.getWarehouseId())){
                break;
            }
            else{
                index++;
            }
        }
        if(index != warehousesOptional.size() && index != 0){
            warehousesOptional.remove(index);
            warehousesOptional.add(0,checkInResp.getWarehouseId());
        }
        else {
            //Todo:Error
        }

        checkInResp.setWarehouseOptionalList(warehousesOptional);
        logger.info("获取入库单" + recordId + "的详细入库信息");
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
    @Transactional
    public void updateWarehouseToCheckin(Long recordId, String warehouseId) {
        logger.info("入库单" + recordId + "更新入库库房为：" + warehouseId);
        siteIOMapper.updateSiteIOWarehouseId(recordId,warehouseId);
    }

    @Override
    @Transactional
    public void updateWarehouseToCheckout(Long recordId, String warehouseId) {
        logger.info("出库单" + recordId + "更新出库库房为：" + warehouseId);
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        //将原库房的逻辑库存加上itemNum
        Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(siteIO.getWarehouseId(),siteIO.getItemId());
        int num = inventory.getLogicInventory() + siteIO.getQty();
        inventory.setLogicInventory(num);
        warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);

        //将修改后的库房的逻辑库存减掉itemNum
        inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(warehouseId,siteIO.getItemId());
        num = inventory.getLogicInventory() - siteIO.getQty();
        inventory.setLogicInventory(num);
        warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);

        //修改库房编号
        siteIOMapper.updateSiteIOWarehouseId(recordId,warehouseId);
    }

    @Override
    @Transactional
    public void insertCheckOutRecord(String type, Long formId, String mainsiteId) {
        List<SiteIO> siteIOList = new ArrayList<>();
        if(type.equals(StatusString.SUPPLY_OUT.getValue())){
            SiteIO siteIO = new SiteIO();
            SupplyIO supplyIO = supplyIOMapper.getSupplyIOByRecordId(formId);
            if(supplyIO == null){
                throw new BusinessErrorException("业务逻辑异常, 该供应商退订录不存在",
                        ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
            }
            siteIO.setFormId(formId);
            siteIO.setItemId(supplyIO.getItemId());
            siteIO.setQty(supplyIO.getQty());
            siteIO.setType(StatusString.SUPPLY_OUT.getValue());

            siteIOList.add(siteIO);
            //Todo :改变供应商退订货单的状态

        }
        else if(type.equals(StatusString.ADJUST_OUT.getValue())){
            AdjustForm adjustForm = adjustFormMapper.getAdjustForm(formId);
            if(adjustForm == null){
                throw new BusinessErrorException("业务逻辑异常, 该调货单订录不存在",
                        ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
            }
            adjustForm.setAdjustStatus(StatusString.A_PROCESSED.getValue());
            adjustFormMapper.updateAdjustFormStatus(adjustForm);

            SiteIO siteIO = new SiteIO();
            siteIO.setType(StatusString.ADJUST_OUT.getValue());
            siteIO.setFormId(formId);
            siteIO.setItemId(adjustForm.getItemId());
            siteIO.setQty(adjustForm.getItemNum());

            siteIOList.add(siteIO);
        }
        else if(type.equals(StatusString.SHIP_OUT.getValue())){
            List<OrderItem> orderItems = taskFormMapper.getTaskItemsByTaskId(formId);
            if(orderItems==null || orderItems.isEmpty()){
                throw new BusinessErrorException("业务逻辑异常, 该任务单订录不存在",
                        ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
            }

            for(OrderItem orderItem:orderItems){
                SiteIO shipOut = new SiteIO();
                shipOut.setFormId(orderItem.getOrderId());
                shipOut.setItemId(orderItem.getItemId());
                shipOut.setQty(orderItem.getItemNum());
                shipOut.setType(type);
                siteIOList.add(shipOut);
            }

            //修改任务单的状态
            taskFormMapper.updateTaskFormStatus(StatusString.T_UNSENT.getValue(),formId);
        }

        for(SiteIO siteIO:siteIOList){
            siteIO.setApprovalStatus(StatusString.WAITING.getValue());
            siteIO.setTimeStamp(new Date());
            siteIO.setApprover("Auto");

            //Todo:这里应该需要增加一些策略
            //此处默认选第一个
            List<String> warehouseOptions = warehouseMapper.getWarehouseOptionsToCheckout(siteIO.getItemId(),siteIO.getQty(),mainsiteId);
            siteIO.setWarehouseId(warehouseOptions.get(0));

            Long nextId = idSequenceUtil.getNextFormIdByName(SequenceName.MAINSITEIO_FORM.getValue());
            siteIO.setRecordId(nextId);
            siteIOMapper.insertSiteIORecord(siteIO);
            logger.info("生成出库记录，并保存到数据库，出库单编号为"+ nextId);

            ItemCheckoutResp itemCheckoutResp = getItemCheckoutRespByRecordId(siteIO.getRecordId());

            //判断是否需要审核, 如果需要发送消息
            //boolean flag = isCheckNeeded();
            boolean flag = true; //测试中全部都需要审核
            if(flag){
                logger.info("出库记录" + nextId + "需要人工审核，下面发送出库消息");
                sendItemCheckoutMessage(itemCheckoutResp);
             return;
            }

            //TODO: 正常情况取消掉注释
            //confirmSiteIORecord(); //审核结束后的出口
        }

    }

    @Override
    public void sendItemCheckoutMessage(ItemCheckoutResp itemCheckoutResp) {
        JSONObject msg = new JSONObject();

        msg.put("type", itemCheckoutResp.getType());
        msg.put("typeDesc", itemCheckoutResp.getTypeDesc());
        msg.put("formId", itemCheckoutResp.getFormId());
        msg.put("itemId", itemCheckoutResp.getItemId());
        msg.put("itemNum",itemCheckoutResp.getItemNum());
        msg.put("recordId",itemCheckoutResp.getRecordId());


        //向待审核出库消息队列发送消息unreviewed item out
        amqpTemplate.convertAndSend("unreviewed item out", msg.toJSONString());

        logger.info("成功发送出库消息: " + msg.toJSONString());
    }

    @Override
    public ItemCheckoutResp getItemCheckoutRespByRecordId(Long recordId) {
        SiteIO siteIO = siteIOMapper.getSiteIORecordById(recordId);
        System.out.println(siteIO.getType());
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

        logger.info("获取出库单" + recordId +"的出库消息");
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

        //验证入库库房是否与查询所得相一致，避免提供的入库编号并不是该主站的记录，同时将入库库房作为可供入库选择的第一个元素
        int index = 0;
        for (String warehouseId:warehousesOptional
        ) {
            if(warehouseId.equals(checkoutResp.getWarehouseId())){
                break;
            }
            else{
                index++;
            }
        }
        if(index != warehousesOptional.size() && index != 0){
            warehousesOptional.remove(index);
            warehousesOptional.add(0,checkoutResp.getWarehouseId());
        }
        else {
            //Todo:Error
        }

        checkoutResp.setWarehouseOptionalList(warehousesOptional);
        logger.info("获取出库单"+ recordId+"详细信息");
        return checkoutResp;

    }

    @Override
    public boolean isCheckNeeded(SiteIOAddReq siteIOAddReq) {
        //Todo: 调用一些策略
        return true;
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
        System.out.println(typeString);
        System.out.println();
        if(typeString.equals(StatusString.SUPPLY_IN.getValue())){
            return 1;
        }
        else if(typeString.equals(StatusString.ADJUST_IN.getValue())){
            return 2;
        }
        else if(typeString.equals(StatusString.RETURN_IN.getValue())){
            return 3;
        }
        else if(typeString.equals(StatusString.CHANGE_IN.getValue())){
            return 4;
        }
        else {
            //Todo:报错

            return -1;
        }
    }

    //1：退货给供应商，2：调货出库，3：发货出库
    int checkoutTypeStringToInteger(String typeString){
        if(typeString.equals(StatusString.SUPPLY_OUT.getValue())){
            return 1;
        }
        else if(typeString.equals(StatusString.ADJUST_OUT.getValue())){
            return 2;
        }
        else if(typeString.equals(StatusString.SHIP_OUT.getValue())){
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
