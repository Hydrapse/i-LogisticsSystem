package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.exception.*;
import com.tcsquad.ilogistics.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hydra
 * @date 2020/7/8
 * @description:
 */

@RestController
public class TestController {

    @Autowired
    TestMapper testMapper;

    @GetMapping("/test/db")
    String testDB(String testId) {
        return testMapper.getTestNameByTestId(testId);
    }

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

}
