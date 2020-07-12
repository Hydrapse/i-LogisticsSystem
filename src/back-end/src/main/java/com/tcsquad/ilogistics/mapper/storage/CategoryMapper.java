package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    //获取分类信息
    Category getCategoryById(String categoryId);

    //仓储管理整体商品信息
    List<Category> getCategoryList();




}
