package com.tcsquad.ilogistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.ItemInventoryGetReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.ItemInventoryResp;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.service.interf.ItemService;
import com.tcsquad.ilogistics.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemMapper itemMapper;

    @Override
    public PageResult getItemInventoryRespByRequest(ItemInventoryGetReq req, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        //只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页。
        //将前台分页查询参数传入并拦截MyBtis执行实现分页效果
        PageHelper.startPage(pageNum, pageSize);

        List<ItemInventoryResp> itemInventorys = itemMapper.getItemInventoryByRequest(req);

        return PageUtil.getPageResult(pageRequest, new PageInfo<>(itemInventorys));
    }
}
