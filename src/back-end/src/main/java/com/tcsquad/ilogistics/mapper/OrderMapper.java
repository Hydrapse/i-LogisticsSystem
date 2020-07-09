package com.tcsquad.ilogistics.mapper;

import com.tcsquad.ilogistics.domain.Order;
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
