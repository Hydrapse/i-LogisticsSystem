package com.tcsquad.ilogistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.exception.*;
import com.tcsquad.ilogistics.mapper.TestMapper;
import com.tcsquad.ilogistics.util.OSSClientUtil;
import com.tcsquad.ilogistics.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hydra
 * @date 2020/7/8
 * @description:
 */

@Controller
public class TestController {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH");

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    OSSClientUtil ossClientUtil;

    @Autowired
    TestMapper testMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/test/db")
    String testDB(String testId) {
        return testMapper.getTestNameByTestId(testId);
    }

    @ResponseBody
    @GetMapping("/test/exception")
    String testError(@RequestParam(defaultValue = "-1") Integer id) throws GlobalException {
        switch (id){
            case 404: throw new NotFoundException("NOT_FOUND_EXCEPTION TEST",
                    ErrorCode.USER_NOT_FOUND.getCode());
            case 400: throw new BusinessErrorException("业务逻辑异常, 订单重复提交",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
            case 500: throw new InternalServerErrorException("后端服务器异常, 消息队列服务器宕机",
                    ErrorCode.MESSAGE_QUEUE_DOWNTIME.getCode());
            case 403: throw new ForbiddenAccessException("用户组权限不足, 无法访问接口");
        }
        return "Wrong Test Id";
    }


    @GetMapping("/test/image")
    String getImageForm(){
        return "imageUpload";
    }

    @ResponseBody
    @PostMapping("/test/image")
    //value值需要和input里面name值一样
    String imageUpload(@RequestParam(value="file")MultipartFile img){
        if (img == null)
            return "isnull";
        String url = ossClientUtil.checkImage(img, OSSClientUtil.PRODUCT_IMAGE_DIR);
        logger.info("图片已存入服务器, 返回链接为:" + url);

        return url;
    }

    @ResponseBody
    @GetMapping("/test/date")
    String getDate(){
        return dateFormat.format(new Date());
    }

    @ResponseBody
    @GetMapping("/test/redis")
    Object testRedis(){
//        redisUtil.set("ts", "我叼你妈的比");

        //模拟存入缺货数据
        JSONObject obj1 = new JSONObject();
        obj1.put("oid", "10000001");
        obj1.put("tid", "2020070602"); //需要数量
        obj1.put("num", "3"); //需要数量

        JSONObject obj2 = new JSONObject();
        obj2.put("oid", "10000002");
        obj2.put("tid", "2020071301"); //需要数量
        obj2.put("num", "7"); //需要数量

        //对应主站id
        String mid1 = "MAIN-001";
        String mid2 = "MAIN-002";
        String mid3 = "MAIN-003";
        String mid4 = "MAIN-004";
        String mid5 = "MAIN-005";

        //对应itemId
        String iid1 = "A-001";
        String iid2 = "A-002";
        // ...

        //ListId
        String listId1 = mid1 + "|" +iid1;
//        redisUtil.lSet(listId1, obj1);
//        redisUtil.lSet(listId1, obj2);
//        redisUtil.lSet(listId1, obj1);
//        redisUtil.lSet(listId1, obj2);
//        redisUtil.lSet(listId1, obj1);
//        redisUtil.lSet(listId1, obj2);

        //获取数据

        Long size = redisUtil.lGetListSize(listId1);
        redisTemplate.opsForList().trim(listId1, 3, size-1);
        Object rtn = redisUtil.lGet(listId1, 0, 2);
        System.out.println(redisUtil.lGetListSize(listId1));


        //删除List
//        redisUtil.del(listId1);

        return rtn;
    }


}
