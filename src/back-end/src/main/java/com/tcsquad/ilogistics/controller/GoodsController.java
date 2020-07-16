package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.ItemInventoryDetailResp;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.exception.NotFoundException;
import com.tcsquad.ilogistics.service.GoodsService;
import com.tcsquad.ilogistics.util.IDSequenceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

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

    @ApiOperation("新增商品")
    @PostMapping("/items")
    public void insertItem(Item item,MultipartFile imgFile) {
        logger.info(item.getCategoryId());
        if(StringUtils.isEmpty(item.getName())){
            logger.warn("item插入时name为空");
            throw new BusinessErrorException("业务逻辑异常, item名称为空",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
        }

        //若img不为空则插入图片
        goodsService.setItemImage(item,imgFile);
        Item rtnItem = goodsService.createItem(item);
        if(rtnItem == null){
            throw new BusinessErrorException("业务逻辑异常, 传入值包含非法值",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
        }
    }

    @ApiOperation("更新商品信息,如果不上传图片默认不修改图片")
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable("itemId")String itemId,
                           Item item,MultipartFile imgFile){
        //如果字符串为空直接报错
        if(StringUtils.isEmpty(itemId)){
            throw new BusinessErrorException("业务逻辑异常, 传入productId为空",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
        }
        else if(StringUtils.isEmpty(item.getName())){
            throw new BusinessErrorException("业务逻辑异常, item名称为空",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
        }

        //检验itemId是否正确
        Item preItem = goodsService.getItemById(itemId);
        if(preItem == null){
            throw new NotFoundException("找不到对应item",
                    ErrorCode.PARAMS_ERROR.getCode());
        }

        //更新商品
        item.setImgUrl(preItem.getImgUrl()); //先将原imgUrl传入
        goodsService.setItemImage(item, imgFile); //若imgFile不为空则插入
        Item rtnItem = goodsService.updateItem(item);
        if(rtnItem == null){
            throw new BusinessErrorException("业务逻辑异常, item名称为空",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
        }

    }
}
