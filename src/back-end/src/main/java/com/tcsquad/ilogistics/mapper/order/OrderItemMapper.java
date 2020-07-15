package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.OrderItem;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface OrderItemMapper {
    List<OrderItem> getOrderItemsByOrderId(long orderId);

    void insertOrderItem(OrderItem orderItem);

    OrderItem getOrderItemByTaskId(long taskId);

    //更新订单商品项的状态信息：调/补货成功
    void updateOrderItemStatus(OrderItem orderItem);

    //注销一个任务单时，设置orderitem的taskid为空
    void updateOrderItemTaskId(OrderItem orderItem);
}
