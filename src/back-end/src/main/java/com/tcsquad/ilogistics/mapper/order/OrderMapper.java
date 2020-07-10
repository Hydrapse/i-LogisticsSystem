package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrders();

    Order getOrder(long orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);
    //更新订单处理状态:processStatus
    void updateOrderStatus(Order order);

    //void updateOrder(Order order);

    void deleteOrderByOrderId(long orderId);


}
