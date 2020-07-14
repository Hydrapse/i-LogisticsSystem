package com.tcsquad.ilogistics.service;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.domain.storage.Category;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.mapper.storage.CategoryMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
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
}
