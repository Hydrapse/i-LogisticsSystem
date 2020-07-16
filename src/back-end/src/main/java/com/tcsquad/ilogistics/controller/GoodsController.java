package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.ItemInventoryDetailResp;
import com.tcsquad.ilogistics.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/14
 * @description: 整体商品信息管理
 */
@Api(tags = "整体商品信息管理模块API接口")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation("获取大类+商品目录")
    @GetMapping("/catalog")
    public List getCatalog() {
        return goodsService.getCatalog();
    }

    @ApiOperation("获取大类列表")
    @GetMapping("/categories")
    public List getCategoryList() {
        return goodsService.getCategoryList();
    }

    @ApiOperation("获取商品列表")
    @GetMapping("/items")
    public PageResult getItemList(String[] categoryIdList, String keyword, PageRequest pageRequest) {
        //初始校验pageRequest
        pageRequest.initialValidate(1, 6);

        return goodsService.getItemByRequest(categoryIdList,keyword,pageRequest);
    }

    @ApiOperation("查询商品详细信息")
    @GetMapping("/items/{itemId}")
    public ItemInventoryDetailResp getItemInventoryDetail(@PathVariable("itemId")String itemId) {
        return goodsService.getItemInventoryDetail(itemId);
    }
}
