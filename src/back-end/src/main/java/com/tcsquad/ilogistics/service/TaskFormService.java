package com.tcsquad.ilogistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.map.RouteResult;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.RouteResp;
import com.tcsquad.ilogistics.domain.response.StatusStatisticsResp;
import com.tcsquad.ilogistics.domain.response.TaskFormLogResp;
import com.tcsquad.ilogistics.domain.storage.SubSite;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.mapper.order.OrderItemMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import com.tcsquad.ilogistics.service.interf.WarehouseService;
import com.tcsquad.ilogistics.util.IDSequenceUtil;
import com.tcsquad.ilogistics.util.PageUtil;
import com.tcsquad.ilogistics.util.StockOutMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskFormService {

    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    AddressService addressService;

    @Autowired
    StockOutMsgUtil stockOutMsgUtil;

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    WarehouseService warehouseService;

    @Autowired IDSequenceUtil idSequenceUtil;

    @Autowired
    SiteIOService siteIOService;

    @Autowired
    LogicalInventoryService logicalInventoryService;

    public TaskForm getTaskForm(Long taskFormId) {
        if (taskFormId != null)
            return taskFormMapper.getTaskForm(taskFormId);
        else
            throw new BusinessErrorException("任务单id不能为空", ErrorCode.MISS_PARAMS.getCode());
    }

    public PageResult getAllTaskForms(PageRequest pageRequest) {
        checkPageRequest(pageRequest);
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        var result = taskFormMapper.getTaskForms();
        var vo = toTaskFormLogs(result);
        return PageUtil.getPageResult(pageRequest, new PageInfo<>(vo));
    }

    public PageResult searchTaskForms(String[] keywords, PageRequest pageRequest) {
        checkPageRequest(pageRequest);
        if (keywords == null)
            return getAllTaskForms(pageRequest);

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        var result = taskFormMapper.searchTaskForms(keywords);
        var vo = toTaskFormLogs(result);
        return PageUtil.getPageResult(pageRequest, new PageInfo<>(vo));
    }

    public PageResult getTaskFormsBySubSiteId(String subSiteId, PageRequest pageRequest) {
        checkPageRequest(pageRequest);
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        var result = taskFormMapper.getTaskFormsBySubSiteId(subSiteId);
        var vo = toTaskFormLogs(result);
        return PageUtil.getPageResult(pageRequest,new PageInfo<>(vo));
    }

    public PageResult searchTaskFormsBySubSiteAndKeyword(String subSiteId, String[] keywords, PageRequest pageRequest) {
        if (keywords == null)
            return getTaskFormsBySubSiteId(subSiteId,pageRequest);

        checkPageRequest(pageRequest);
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        var result = taskFormMapper.getTaskFormsBySubsiteAndKeyword(subSiteId, keywords);
        var vo = toTaskFormLogs(result);
        return PageUtil.getPageResult(pageRequest,new PageInfo<>(vo));
    }

    public PageResult searchTaskFormsBySubSiteAndStatus(String subSiteId, String status, PageRequest pageRequest) {
        if (status == null)
            throw new BusinessErrorException("状态不能为空", ErrorCode.MISS_PARAMS.getCode());

        checkPageRequest(pageRequest);
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        var result = taskFormMapper.getTaskFormsBySubsiteAndStatus(subSiteId, status);

        var vo = toTaskFormLogs(result);
        return PageUtil.getPageResult(pageRequest,new PageInfo<>(vo));
    }

//    private void getOrderItems(TaskForm taskForm){
//        taskForm.setOrderItems(taskFormMapper.getTaskItemsByTaskId(taskForm.getTaskId()));
//    }
//
//    private void getOrderItems(List<TaskForm> taskForms) {
//        for(var taskForm :taskForms) {
//            getOrderItems(taskForm);
//        }
//    }

    public List<StatusStatisticsResp> countStatusOfAllTaskForms() {
        var result = new ArrayList<StatusStatisticsResp>();
        var status = List.of(
                Pair.of(StatusString.T_WAITING.getValue(), "缺货"),
                Pair.of(StatusString.T_UNSENT.getValue(), "待发货"),
                Pair.of(StatusString.T_ON_THE_WAY.getValue(), "运输中"),
                Pair.of(StatusString.T_NOT_DELIVERED.getValue(), "配送中"),
                Pair.of(StatusString.T_ACCEPTED.getValue(), "已签收")
        );
        for (var state : status)
            result.add(new StatusStatisticsResp(state.getSecond(), taskFormMapper.countTaskFormsByStatus(state.getFirst())));
        return result;
    }

    public RouteResp getRoute(Long taskFormId) {//todo：statusMessage
        if (taskFormId == null)
            throw new BusinessErrorException("任务单id不能为空", ErrorCode.MISS_PARAMS.getCode());
        var taskForm = taskFormMapper.getTaskForm(taskFormId);
        if (taskForm == null)
            throw new BusinessErrorException("任务单不存在", ErrorCode.PARAMS_ERROR.getCode());

        var subSite = siteMapper.getSubSiteById(taskForm.getSubSiteId());
        var mainSite = siteMapper.getMainSiteById(subSite.getMainsiteId());
        var destination = addressService.getPosition(taskForm.getBillPro() + taskForm.getBillCity() + taskForm.getBillDis() + taskForm.getBillAddr(), taskForm.getBillCity());
        var route = new RouteResult.Route[2];
        route[0] = addressService.route(
                Pair.of(mainSite.getLatitude().doubleValue(), mainSite.getLongitude().doubleValue()),
                Pair.of(subSite.getLatitude().doubleValue(), subSite.getLongitude().doubleValue())
        ).getResult().getRoutes().get(0);
        route[1] = addressService.route(
                Pair.of(subSite.getLatitude().doubleValue(), subSite.getLongitude().doubleValue()),
                Pair.of(destination.getLat(), destination.getLng())
        ).getResult().getRoutes().get(0);

        var result = new RouteResp.Result[2];
        for(int i=0;i<2;i++) {
            result[i] = new RouteResp.Result();
            result[i].setDistance(route[i].getDistance());
            result[i].setDuration(route[i].getDuration());
            var list = new ArrayList<RouteResp.Step>();
            for (var item : route[i].getSteps()) {
                var tmp = new RouteResp.Step();
                var start = item.getStart_location();
                var end = item.getEnd_location();

                tmp.setLeg_index(item.getLeg_index());
                tmp.setStart_location(new RouteResp.Point(start.getLng(), start.getLat()));
                tmp.setEnd_location(new RouteResp.Point(end.getLng(), end.getLat()));
                list.add(tmp);
            }
            result[i].setSteps(list);
        }

        return new RouteResp(result[0],result[1]);
    }

    /**
     * 核心功能，生成任务单
     * 缺少shipxxx信息和配送员信息
     *
     * @param order      订单
     * @param mainsiteId 主站id
     */
    public void generateTaskForms(Order order, String mainsiteId) {
        //计算最近配送站
        var subsites = siteMapper.getSubSiteListByMainSiteId(mainsiteId);
        var subsitesPositions  = new ArrayList<Pair<Double,Double>>();
        for(SubSite subSite:subsites) {
            subsitesPositions.add(Pair.of(subSite.getLatitude().doubleValue(),subSite.getLongitude().doubleValue()));
        }
        var destination = addressService.getPosition(order.getBillPro() + order.getBillCity() + order.getBillDistrict() + order.getBillAddr(), order.getBillCity());
        var destinationPoint = Pair.of(destination.getLat(),destination.getLng());
        int closest = addressService.closest(destinationPoint,subsitesPositions);
        String subSiteId = subsites.get(closest).getSubsiteId();

        //获取商品列表
        var items = orderItemMapper.getOrderItemsByOrderId(order.getOrderId());

        //生成任务单
        var inStockList = new ArrayList<OrderItem>();
        for (var item : items) {

            if(logicalInventoryService.decreaseLogicInventory
                    (mainsiteId, item.getItemId(), item.getItemNum())) {
                //TODO: 扣除逻辑库存成功后
                inStockList.add(item);
            } else {
                var taskForm = preGenerateTaskForm(order,subSiteId);

                item.setStatus(StatusString.ITEM_STOCK_OUT.getValue());
                item.setTaskId(taskForm.getTaskId());
                orderItemMapper.updateOrderItemTaskId(item);
                orderItemMapper.updateOrderItemStatus(item);

                taskForm.setStatus(StatusString.T_WAITING.getValue());
                taskForm.setOrderItems(List.of(item));
                taskFormMapper.insertTaskForm(taskForm);

                //TODO:生成调货单
                stockOutMsgUtil.insertStockOutMessage(mainsiteId,taskForm.getOrderItems().get(0));
            }
        }
        if(!inStockList.isEmpty()) {
            var taskForm = preGenerateTaskForm(order,subSiteId);
            for (var item: inStockList) {
                item.setStatus(StatusString.ITEM_PREPARED.getValue());
                item.setTaskId(taskForm.getTaskId());
                orderItemMapper.updateOrderItemTaskId(item);
                orderItemMapper.updateOrderItemStatus(item);
            }

            taskForm.setStatus(StatusString.T_UNSENT.getValue());
            taskForm.setOrderItems(inStockList);
            taskFormMapper.insertTaskForm(taskForm);

            sendTaskForm(taskForm.getTaskId(),mainsiteId,"张三","10086");//TODO:发货人信息
        }
    }

    /**
     * 发送任务单（按主站确认发货地）
     * @param taskFormId 任务单id
     * @param shipName 发货人姓名
     * @param shipTel 发货人电话
     * @param mainSiteId 主站id
     */
    public void sendTaskForm(long taskFormId,String mainSiteId,String shipName,String shipTel) {
        var mainSite = siteMapper.getMainSiteById(mainSiteId);
        sendTaskForm(taskFormId, shipName, shipTel, mainSite.getProvince(),mainSite.getCity(),mainSite.getDistrict(),mainSite.getAddr());
    }

    /**
     * 发送任务单
     * @param taskFormId 任务单id
     * @param shipName 发货人姓名
     * @param shipTel 发货人电话
     * @param shipPro 发货省份
     * @param shipCity 发货城市
     * @param shipDis 发货地区县
     * @param shipAddr 发货地详细地址
     */
    public void sendTaskForm(long taskFormId,String shipName,String shipTel,String shipPro,String shipCity,String shipDis,String shipAddr) {
        var taskForm = taskFormMapper.getTaskForm(taskFormId);
        taskForm.setDeliveryDateTime(new Date());
        taskForm.setShipName(shipName);
        taskForm.setShipTel(shipTel);
        taskForm.setShipPro(shipPro);
        taskForm.setShipCity(shipCity);
        taskForm.setShipDis(shipDis);
        taskForm.setShipAddr(shipAddr);
        taskForm.setStatus(StatusString.T_ON_THE_WAY.getValue());
        taskFormMapper.updateTaskForm(taskForm);

        //TODO: 发送出库消息
        //type: SHIP_OUT
        //siteIOService.insertCheckOutRecord(String type, Long taskId, String mainsiteId);
    }

    private void checkPageRequest(PageRequest pageRequest) {
        if(pageRequest == null || pageRequest.getPageNum()== null || pageRequest.getPageSize() == null)
            throw new BusinessErrorException("分页信息不能为空",ErrorCode.PARAMS_ERROR.getCode());
    }

    private TaskForm preGenerateTaskForm(Order order,String subSiteId) {
        if (order == null)
            throw new BusinessErrorException("order不能为空", ErrorCode.MISS_PARAMS.getCode());

        var taskForm = new TaskForm();
        taskForm.setTaskId(idSequenceUtil.getNextFormIdByName(SequenceName.TASK_FORM.getValue()));
        taskForm.setOrderId(order.getOrderId());
        taskForm.setBillName(order.getBillName());
//        taskForm.setBillTel(order.get);
        taskForm.setBillPro(order.getBillPro());
        taskForm.setBillCity(order.getBillCity());
        taskForm.setBillDis(order.getBillDistrict());
        taskForm.setBillAddr(order.getBillAddr());
        taskForm.setTotalPrice(order.getTotalPrice());
        taskForm.setNote(order.getNote());
        taskForm.setSubSiteId(subSiteId);

        return taskForm;
    }

    public TaskFormLogResp toTaskFormLog(TaskForm taskForm) {
        if (taskForm == null)
            throw new BusinessErrorException("任务单不能为空", ErrorCode.MISS_PARAMS.getCode());

        var result = new TaskFormLogResp();
        result.setTaskFormId(taskForm.getTaskId());
        result.setSubSiteId(taskForm.getSubSiteId());
        result.setStatus(taskForm.getStatus());
//        result.setStatusMessage(getStatusMessage(taskForm,mainSiteId));
        result.setShipTime(taskForm.getDeliveryDateTime());
        result.setReceiverName(taskForm.getBillName());
        result.setReceiverTel(taskForm.getBillTel());
        result.setReceiverAddress(taskForm.getBillPro() + " " + taskForm.getBillCity() + " " + taskForm.getBillDis() + " " + taskForm.getBillAddr());
        result.setOrderItems(taskForm.getOrderItems());
        return result;
    }

    public List<TaskFormLogResp> toTaskFormLogs(List<TaskForm> taskForms) {
        if (taskForms == null)
            throw new BusinessErrorException("任务单列表不能为空", ErrorCode.MISS_PARAMS.getCode());
        var result = new ArrayList<TaskFormLogResp>();
        for (var taskForm : taskForms) {
            result.add(toTaskFormLog(taskForm));
        }
        return result;
    }

    private String getStatusMessage(TaskForm taskForm, String mainSiteId) {
        var mainSite = siteMapper.getMainSiteById(mainSiteId);
        var subSite = siteMapper.getSubSiteById(taskForm.getSubSiteId());
        switch (taskForm.getStatus()){
            case "W":
                return "商品 "+taskForm.getOrderItems().get(0).getItem().getName() + " 缺货, 调货中";
            case "U":
                return "待发货";
            case "O":
                return mainSite.getCity()+mainSite.getDistrict()+" 站已发出";
            case "N":
                return "已到达 " + subSite.getDistrict() + " 站,配送中";
            case "Y":
                return "商品已签收, 感谢您的使用";
            default:
                return "状态信息有误";
        }
    }
}
