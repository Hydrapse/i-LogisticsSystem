package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.request.OrderSelectReq;
import com.tcsquad.ilogistics.domain.response.OrderBriefResp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrders();

    Order getOrder(long orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);

    //更新订单处理状态:processStatus
    void updateProcessStatus(Order order);

    //更新支付状态:payStatus
    void updatePayStatus(Order order);

    //void updateOrder(Order order);

    void deleteOrderByOrderId(long orderId);

    void updateShippingCost(Order order);

    boolean hasOrder(Long orderId);

    List<OrderBriefResp> getOrderBriefsByReq(OrderSelectReq orderSelectReq);

    List<Long> getAllOrderId();

    List<String> getBillNameListByInfix(String infix);
}
