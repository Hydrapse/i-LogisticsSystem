package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.exception.*;
import com.tcsquad.ilogistics.mapper.TestMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.util.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/8
 * @description:
 */

@Controller
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    OSSClientUtil ossClientUtil;

    @Autowired
    TestMapper testMapper;

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



}
