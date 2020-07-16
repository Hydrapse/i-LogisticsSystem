package com.tcsquad.ilogistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.Order;
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
import com.tcsquad.ilogistics.service.interf.WarehouseService;
import com.tcsquad.ilogistics.util.PageUtil;
import com.tcsquad.ilogistics.util.StockOutMsgUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public RouteResp getRoute(Long taskFormId) {
        if (taskFormId == null)
            throw new BusinessErrorException("任务单id不能为空", ErrorCode.MISS_PARAMS.getCode());
        var taskForm = taskFormMapper.getTaskForm(taskFormId);
        if (taskForm == null)
            throw new BusinessErrorException("任务单不存在", ErrorCode.PARAMS_ERROR.getCode());

        var subSite = siteMapper.getSubSiteById(taskForm.getSubSiteId());
        var mainSite = siteMapper.getMainSiteById(subSite.getMainsiteId());
        var destination = addressService.getPosition(taskForm.getBillPro() + taskForm.getBillCity() + taskForm.getBillDis() + taskForm.getBillAddr(), taskForm.getBillCity());
        var route = addressService.route(
                Pair.of(mainSite.getLatitude().doubleValue(), mainSite.getLongitude().doubleValue()),
                List.of(Pair.of(subSite.getLatitude().doubleValue(), subSite.getLongitude().doubleValue())),
                Pair.of(destination.getLat(), destination.getLng())
        ).getResult().getRoutes().get(0);

        var result = new RouteResp();
        result.setDistance(route.getDistance());
        result.setDuration(route.getDuration());
        var list = new ArrayList<RouteResp.Step>();
        for (var item : route.getSteps()) {
            var tmp = new RouteResp.Step();
            var start = item.getStart_location();
            var end = item.getEnd_location();

            tmp.setLeg_index(item.getLeg_index());
            tmp.setStart_location(new RouteResp.Point(start.getLng(), start.getLat()));
            tmp.setEnd_location(new RouteResp.Point(end.getLng(), end.getLat()));
            list.add(tmp);
        }
        result.setSteps(list);
        return result;
    }

    /**
     * 核心功能，生成任务单
     * 缺少shipxxx信息和配送员信息
     *
     * @param order      订单
     * @param mainSiteId 主站id
     * @return 任务单列表
     */
    public List<TaskForm> generateTaskForms(Order order, String mainSiteId) {
        //获取商品列表
        var items = orderItemMapper.getOrderItemsByOrderId(order.getOrderId());

        //计算最近配送站
        var subsites = siteMapper.getSubSiteListByMainSiteId(mainSiteId);
        var subsitesPositions  = new ArrayList<Pair<Double,Double>>();
        var destination = addressService.getPosition(order.getBillPro() + order.getBillCity() + order.getBillDistrict() + order.getBillAddr(), order.getBillCity());
        for(SubSite subSite:subsites) {
            subsitesPositions.add(Pair.of(subSite.getLatitude().doubleValue(),subSite.getLongitude().doubleValue()));
        }
        var distance = addressService.distance(Pair.of(destination.getLat(),destination.getLng()),subsitesPositions);
        int closest = 0;
        double closestValue = distance.get(0).getDistance().getValue();
        for(int i = 1;i<distance.size();i++){
            double tmp = distance.get(i).getDistance().getValue();
            if(tmp < closestValue) {
                closest = i;
                closestValue = tmp;
            }
        }
        String subSiteId = subsites.get(closest).getSubsiteId();

        var inStock = preGenerateTaskForm(order,subSiteId);
        inStock.setStatus(StatusString.T_UNSENT.getValue());
        var outStocks = new ArrayList<TaskForm>();
        for (var item : items) {
            if (warehouseService.getItemInventoryByMainSiteAndItemId(item.getItemId(),mainSiteId) >= item.getItemNum()) { // in stock TODO 判断
                warehouseService.decreaseItemInventoryByMainSiteAndItemId(item.getItemId(),mainSiteId,item.getItemNum());
                item.setStatus(StatusString.ITEM_PREPARED.getValue());
                inStock.getOrderItems().add(item);
            } else {
                item.setStatus(StatusString.ITEM_STOCK_OUT.getValue());
                var form = preGenerateTaskForm(order,subSiteId);
                form.setStatus(StatusString.T_WAITING.getValue());
                form.getOrderItems().add(item);
                outStocks.add(form);
            }
        }
        for (var form : outStocks)
            taskFormMapper.insertTaskForm(form);
        if (inStock.getOrderItems() != null && !inStock.getOrderItems().isEmpty())
            taskFormMapper.insertTaskForm(inStock);

        var all = taskFormMapper.getTaskFormsByOrderId(order.getOrderId());
        for (var form : all) {
            if (form.getStatus().equals(StatusString.T_WAITING.getValue()))
                stockOutMsgUtil.insertStockOutMessage(mainSiteId,form.getOrderItems().get(0));
        }

        return all;
    }

    private void checkPageRequest(PageRequest pageRequest) {
        if(pageRequest == null || pageRequest.getPageNum()== null || pageRequest.getPageSize() == null)
            throw new BusinessErrorException("分页信息不能为空",ErrorCode.PARAMS_ERROR.getCode());
    }

    private TaskForm preGenerateTaskForm(Order order,String subSiteId) {
        if (order == null)
            throw new BusinessErrorException("order不能为空", ErrorCode.MISS_PARAMS.getCode());

        var taskForm = new TaskForm();
        taskForm.setOrderId(order.getOrderId());
        taskForm.setBillName(order.getBillName());
        taskForm.setBillPro(order.getBillPro());
        taskForm.setBillCity(order.getBillCity());
        taskForm.setBillDis(order.getBillDistrict());
        taskForm.setBillAddr(order.getBillAddr());
        taskForm.setTotalPrice(order.getTotalPrice());
        taskForm.setNote(order.getNote());
        taskForm.setOrderItems(new ArrayList<>());
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
}
