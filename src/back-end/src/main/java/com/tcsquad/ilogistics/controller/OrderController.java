package com.tcsquad.ilogistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.order.ReturnForm;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.request.OrderAddReq;
import com.tcsquad.ilogistics.domain.request.OrderSelectReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.OrderDetailResp;
import com.tcsquad.ilogistics.exception.NotFoundException;
import com.tcsquad.ilogistics.service.OrderService;
import com.tcsquad.ilogistics.service.TaskFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/11
 * @description: 订单模块控制层
 */
@Api(tags = "订单管理模块API接口")
@RestController
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    TaskFormService taskFormService;

    @ApiOperation("新增订单入口")
    @PostMapping("/orders")
    public void newOrder(@RequestBody OrderAddReq orderAddReq){
        //新增订单
        orderService.insertOrderReq(orderAddReq);
        Order order = orderAddReq.getOrder();

        //判断订单是否需要被审核
        if (orderService.isCheckNeeded(orderAddReq)){
            //如果需要, 直接发送消息给队列
            orderService.sendOrderMessage(order);
            return;
        }

        //若不需要,直接进行预分拣获取mainsiteId
        //接着传递给任务单生成模块
        String mainsiteId = orderService.preSlotForMainSite(order).getMainsiteId();

        //更新订单状态
        order.setProcessStatus(StatusString.PROCESSING.getValue());
        orderService.updateProcessStatus(order);

        //传递order,mainsiteId给任务单生成模块
//        taskFormService.generateTaskForms(order, mainsiteId);
    }

    @ApiOperation("订单审核详情")
    @GetMapping("/orders/{orderId}/check")
    public OrderDetailResp getCheckDetail(@PathVariable("orderId")Long orderId){
        //获取订单详情
        OrderDetailResp orderDetailResp = orderService.getDetailRespByOrderId(orderId);

        return orderDetailResp;
    }

    @ApiOperation("修改订单信息")
    @PatchMapping("/orders/{orderId}/check")
    public void confirmCheckDetail(@PathVariable("orderId")Long orderId,
                                   String processStatus,
                                   String mainsiteId,
                                   String shippingCost) {
        //更新order信息
        Order order = orderService.confirmOrder(orderId, processStatus, shippingCost);

        //若mainsiteId为空, 则重新执行预分拣,查找主站
        if(StringUtils.isEmpty(mainsiteId)){
            mainsiteId = orderService.preSlotForMainSite(order).getMainsiteId();
        }
        //检查mainsiteId是否正确, 若不正确抛出异常
        orderService.checkMainSiteId(mainsiteId);

        //传递order,mainsiteId给任务单生成模块
//        taskFormService.generateTaskForms(order, mainsiteId);
    }

    @ApiOperation("查询Order列表")
    @GetMapping("/orders")
    public Object getOrderList(OrderSelectReq orderSelectReq, PageRequest pageRequest){
        pageRequest.initialValidate(1, 7);
        PageResult resp = orderService.selectOrders(orderSelectReq, pageRequest);

        return resp;
    }

    @ApiOperation("查询订单详情")
    @GetMapping("/orders/{orderId}")
    public Object getOrderDetail(@PathVariable("orderId")Long orderId){
        Order order = orderService.getOrderById(orderId);
        if(order == null){
            throw new NotFoundException("该订单不存在",
                    ErrorCode.USER_NOT_FOUND.getCode());
        }
        List<OrderItem> orderItemList = orderService.getOrderItemListByOrderId(orderId);
        JSONObject orderDetail = new JSONObject();
        orderDetail.put("order",order);
        orderDetail.put("orderItemList",orderItemList);

        return orderDetail;
    }

    @ApiOperation("根据订单Id查询任务单列表")
    @GetMapping("/orders/{orderId}/taskforms")
    public List getTaskformsByOrderId(@PathVariable("orderId")Long orderId){
        List<TaskForm> taskForms = orderService.getTaskformListByOrderId(orderId);
        return taskForms;
    }

    @ApiOperation("根据订单Id查询退货单列表")
    @GetMapping("/orders/{orderId}/returnforms")
    public List getReturnformsByOrderId(@PathVariable("orderId")Long orderId){
        List<ReturnForm> returnForms = orderService.getReturnformListByOrderId(orderId);
        return returnForms;
    }

}
