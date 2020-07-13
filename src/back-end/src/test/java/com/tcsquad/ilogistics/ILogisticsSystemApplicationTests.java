package com.tcsquad.ilogistics;

import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mq.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@SpringBootTest(classes = ILogisticsSystemApplication.class)
class ILogisticsSystemApplicationTests {

    @Autowired
    Sender sender;

    @Autowired
    TaskFormMapper taskFormMapper;

    @Test
    void test() {
        sender.send();
    }

    @Test
    void testTaskForm() {
        BigDecimal doubleStr = new BigDecimal(Double.toString(99.9));
        TaskForm taskForm1 = new TaskForm();
        taskForm1.setTaskId(2020071301);
        taskForm1.setOrderId(10000000);
        taskForm1.setSubSiteId("SUB-S-002");
        taskForm1.setShipName("配送站SUB-S-002");
        taskForm1.setShipAddr("金渭路25号");
        taskForm1.setShipTel("15600988900");
        taskForm1.setShipPro("陕西省");
        taskForm1.setShipCity("宝鸡市");
        taskForm1.setShipDis("渭滨区");
        taskForm1.setBillName("姜美玉");
        taskForm1.setBillTel("15436780965");
        taskForm1.setBillPro("陕西省");
        taskForm1.setBillCity("西安市");
        taskForm1.setBillDis("未央区");
        taskForm1.setBillAddr("东风路灞河桥西");
        taskForm1.setStatus("Y");
        taskForm1.setCourier("周其之");
        taskForm1.setTotalPrice(doubleStr);
        taskForm1.setNote("");

        taskFormMapper.insertTaskForm(taskForm1);

    }

}
