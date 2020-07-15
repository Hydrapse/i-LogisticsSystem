package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.response.RouteResp;
import com.tcsquad.ilogistics.domain.response.StatusStatisticsResp;
import com.tcsquad.ilogistics.domain.response.TaskFormLogResp;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.mapper.order.OrderItemMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
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
    AmqpTemplate amqpTemplate;

    @Autowired
    SiteMapper siteMapper;

    public TaskForm getTaskForm(Long taskFormId) {
        if (taskFormId != null)
            return taskFormMapper.getTaskForm(taskFormId);
        else
            throw new BusinessErrorException("任务单id不能为空", ErrorCode.MISS_PARAMS.getCode());
    }

    public List<TaskForm> getAllTaskForms() {
        return taskFormMapper.getTaskForms();
    }

    public List<TaskForm> searchTaskForms(String[] keywords) {
        var res = new ArrayList<TaskForm>();
        if (keywords == null)
            return getAllTaskForms();

        for (String keyword : keywords) {
            res.addAll(taskFormMapper.searchTaskForms(keyword));
        }
        return res;
    }

    public List<TaskForm> getTaskFormsBySubSiteId(String subSiteId) {
        if (subSiteId == null)
            return new ArrayList<>();
        else {
            var tmp = taskFormMapper.getTaskFormsBySubSiteId(subSiteId);
            var result = new ArrayList<TaskForm>();
            for (var taskForm : tmp) {
                if (taskForm.getStatus().equals("Y") || taskForm.getStatus().equals("N"))
                    result.add(taskForm);
            }
            return result;
        }
    }

    /**
     * 核心功能，生成任务单
     *
     * @param order      订单
     * @param mainSiteId 主站id
     * @return 任务单列表
     */
    public List<TaskForm> generateTaskForms(Order order, String mainSiteId) {
        var items = orderItemMapper.getOrderItemsByOrderId(order.getOrderId());
        var destination = addressService.getPosition(order.getBillPro() + order.getBillCity() + order.getBillDistrict() + order.getBillAddr(), order.getBillCity());


        var inStock = preGenerateTaskForm(order);
        //courier;ship xxx;subsite;
        inStock.setStatus("N");

        var outStocks = new ArrayList<TaskForm>();
        for (var item : items) {
            if (false) { // in stock TODO 判断
                item.setStatus("P");
                inStock.getOrderItems().add(item);
                // TODO 改库存
            } else {
                item.setStatus("O");
                var form = preGenerateTaskForm(order);
                form.setStatus("W");
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
            if (form.getStatus().equals("W"))
                amqpTemplate.convertAndSend("lack item", form.getOrderItems().get(0).getItemId(), form.getTaskId());
        }

        return all;
    }

    private TaskForm preGenerateTaskForm(Order order) {
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
        result.setSendAddress(taskForm.getShipPro() + " " + taskForm.getShipCity() + " " + taskForm.getShipDis() + " " + taskForm.getShipAddr());
        result.setReceiveAddress(taskForm.getBillPro() + " " + taskForm.getBillCity() + " " + taskForm.getBillDis() + " " + taskForm.getBillAddr());
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

    public List<TaskForm> searchTaskFormsBySubSiteAndKeyword(String subSiteId, String[] keywords) {
        var res = new ArrayList<TaskForm>();
        if (subSiteId == null || keywords == null)
            return getTaskFormsBySubSiteId(subSiteId);

        for (String keyword : keywords) {
            res.addAll(taskFormMapper.getTaskFormsBySubsiteAndKeyword(subSiteId, keyword));
        }
        return res;
    }

    public List<TaskForm> searchTaskFormsBySubSiteAndStatus(String subSiteId, String status) {
        if (subSiteId == null || status == null)
            throw new BusinessErrorException("状态不能为空", ErrorCode.MISS_PARAMS.getCode());
        else
            return taskFormMapper.getTaskFormsBySubsiteAndStatus(subSiteId, status);
    }

    public RouteResp getRoute(Long taskFormId) {
        if(taskFormId == null)
            throw new BusinessErrorException("任务单id不能为空",ErrorCode.MISS_PARAMS.getCode());
        var taskForm = taskFormMapper.getTaskForm(taskFormId);
        if(taskForm == null)
            throw new BusinessErrorException("任务单不存在",ErrorCode.PARAMS_ERROR.getCode());
        var subSite = siteMapper.getSubSiteById(taskForm.getSubSiteId());
        var destination = addressService.getPosition(taskForm.getBillPro() + taskForm.getBillCity() + taskForm.getBillDis() + taskForm.getBillAddr(), taskForm.getBillCity());
        var route = addressService.route(
                Pair.of(subSite.getLongitude().doubleValue(), subSite.getLatitude().doubleValue()),
                Pair.of(destination.getLng(), destination.getLat())
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
}
