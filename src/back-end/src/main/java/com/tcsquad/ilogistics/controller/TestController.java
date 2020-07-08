package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/testdb")
    String test(String testId){
        return testMapper.getTestNameByTestId(testId);
    }

}
