package com.tcsquad.ilogistics;

import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.util.RedisUtil;
import com.tcsquad.ilogistics.util.StockOutMsgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest(classes = ILogisticsSystemApplication.class)
class ILogisticsSystemApplicationTests {

//    @Autowired
//    Sender sender;

    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    StockOutMsgUtil stockOutUtil;

    @Autowired
    RedisUtil redisUtil;
//    @Test
//    void test() {
//        sender.send();
//    }

    @Test
    void insertRedis() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemNum(1);
        orderItem.setTaskId(111);
        orderItem.setItemId("A-005");
        stockOutUtil.insertStockOutMessage("MAIN-001", orderItem);

        orderItem.setItemNum(2);
        orderItem.setTaskId(222);
        orderItem.setItemId("A-005");
        stockOutUtil.insertStockOutMessage("MAIN-001", orderItem);

        orderItem.setItemNum(3);
        orderItem.setTaskId(333);
        orderItem.setItemId("A-005");
        stockOutUtil.insertStockOutMessage("MAIN-001", orderItem);

        orderItem.setItemNum(4);
        orderItem.setTaskId(444);
        orderItem.setItemId("A-006");
        stockOutUtil.insertStockOutMessage("MAIN-001", orderItem);

        orderItem.setItemNum(5);
        orderItem.setTaskId(555);
        orderItem.setItemId("A-006");
        stockOutUtil.insertStockOutMessage("MAIN-001", orderItem);
    }

    @Test
    void pickRedis(){
//        stockOutUtil.setMsgThroughput(3);
//        List list = stockOutUtil.consumeStockOutMessage("MAIN-001", "A-006");
//        System.out.println("pick: " + list.toString());
//        List subList = list.subList(1, 2);
//        System.out.println("rowback: " + subList.toString());
//        stockOutUtil.rowbackStockOutMessage("MAIN-001", "A-006", subList);

    }

    @Test
    void test(){
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        System.out.println(objects.subList(0,0).size());
    }


}
