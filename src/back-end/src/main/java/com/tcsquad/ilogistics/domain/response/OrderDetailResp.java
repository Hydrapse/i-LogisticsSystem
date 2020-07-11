package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailResponse {
    Order order;
    List<OrderItem> orderItemList = new ArrayList<>();

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
}
