package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrders();

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void updateOrderStatus(String status,int orderId);//这里的status是指processstatus

    void updateOrder(Order order);

    void deleteOrderByOrderId(int orderId);


}
