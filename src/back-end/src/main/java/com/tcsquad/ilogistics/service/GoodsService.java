package com.tcsquad.ilogistics.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.ItemInventoryDetailResp;
import com.tcsquad.ilogistics.domain.storage.Category;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.domain.storage.MainsiteInventory;
import com.tcsquad.ilogistics.mapper.storage.CategoryMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.mapper.storage.WarehouseMapper;
import com.tcsquad.ilogistics.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/14
 * @description:
 */
@Service
public class GoodsService {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    WarehouseMapper warehouseMapper;

    public List getCatalog(){
        List list = new ArrayList();

        List<Category> categoryList = categoryMapper.getCategoryList();
        for (Category category : categoryList){
            JSONObject categoryObj = new JSONObject();
            categoryObj.put("categoryId", category.getCategoryId());
            categoryObj.put("categoryName", category.getName());
            categoryObj.put("categoryImg", category.getDescn());

            List<Item> itemList = itemMapper.getItemListByCategoryId(category.getCategoryId());
            List newList = new ArrayList();
            for (Item item : itemList){
                JSONObject itemObj = new JSONObject();
                itemObj.put("itemId", item.getItemId());
                itemObj.put("itemName", item.getName());
                itemObj.put("descn", item.getDescn());
                itemObj.put("unitCost", item.getUnitCost());
                itemObj.put("listPrice", item.getListPrice());
                itemObj.put("status", item.getStatus());
                newList.add(itemObj);
            }
            categoryObj.put("itemList", newList);

            list.add(categoryObj);
        }
        return list;
    }

    public List getCategoryList(){
        List<Category> categories = categoryMapper.getCategoryList();

        List list = new ArrayList();
        for (Category category : categories){
            JSONObject categoryObj = new JSONObject();
            categoryObj.put("categoryId", category.getCategoryId());
            categoryObj.put("categoryName", category.getName());
            categoryObj.put("categoryImg", category.getDescn());

            list.add(categoryObj);
        }
        return list;
    }


    //根据请求来获取商品列表
    public PageResult getItemByRequest(String[] categoryIdList,String keyword, PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        //只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页。
        //将前台分页查询参数传入并拦截MyBtis执行实现分页效果
        PageHelper.startPage(pageNum, pageSize);

        List<Item> items = itemMapper.getItemListByRequest(categoryIdList,keyword);

        return PageUtil.getPageResult(pageRequest, new PageInfo<>(items));
    }


    //查询该商品的信息以及该商品在各主站分布状态
    public ItemInventoryDetailResp getItemInventoryDetail(String itemId){
        ItemInventoryDetailResp detailResp = new ItemInventoryDetailResp();
        Item item = itemMapper.getItem(itemId);
        detailResp.setItem(item);
        List<MainsiteInventory> mainsiteInventoryList = warehouseMapper.getMainsiteInventorysByItemId(itemId);
        detailResp.setMainsiteInventoryList(mainsiteInventoryList);
        int totalInventory = 0;
        for (MainsiteInventory i: mainsiteInventoryList){
            totalInventory += i.getItemInventory();
        }
        detailResp.setTotalInventory(totalInventory);

        return detailResp;
    }
}
