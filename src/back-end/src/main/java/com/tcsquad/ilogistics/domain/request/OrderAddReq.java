package com.tcsquad.ilogistics.domain.request;

import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;

import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/11
 * @description: 新增订单请求DTO
 */
public class OrderAddReq {
    Order order;
    List<OrderItem> orderItemList;
    boolean manual; //是否为手动新增订单￿

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }
}
