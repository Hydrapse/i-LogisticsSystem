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

}
