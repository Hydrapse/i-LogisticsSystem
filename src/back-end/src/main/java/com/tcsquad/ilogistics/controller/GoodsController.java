package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/14
 * @description: 整体商品信息管理
 */
@Api(tags = "整体商品信息管理模块API接口")
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation("获取大类+商品目录")
    @GetMapping("/goods/catalog")
    public List getCatalog() {
        return goodsService.getCatalog();
    }
}
