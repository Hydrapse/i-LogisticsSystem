package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.storage.MainSite;
import com.tcsquad.ilogistics.domain.storage.SubSite;

import java.util.List;

public class OrderDetailResp {
    Order order;
    List<OrderItem> orderItemList;
    MainSite mainsite;
    SubSite subSite;
    String msg;

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

    public MainSite getMainsite() {
        return mainsite;
    }

    public void setMainsite(MainSite mainsite) {
        this.mainsite = mainsite;
    }

    public SubSite getSubSite() {
        return subSite;
    }

    public void setSubSite(SubSite subSite) {
        this.subSite = subSite;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
