package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {
    //获取分类信息
    Category getCategoryById(String cid);




}
