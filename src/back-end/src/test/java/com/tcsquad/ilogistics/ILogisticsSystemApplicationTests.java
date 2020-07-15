package com.tcsquad.ilogistics;

import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ILogisticsSystemApplication.class)
class ILogisticsSystemApplicationTests {

//    @Autowired
//    Sender sender;

    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    RedisUtil redisUtil;

//    @Test
//    void test() {
//        sender.send();
//    }

    @Test
    void testRedis() {
    }


}
