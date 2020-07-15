package com.tcsquad.ilogistics.util;

import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.PageRequest;


/**
 * @author Hydra
 * @date 2020/6/16
 * @description: 分页查询相关工具类
 */

public class PageUtil {
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @return
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());

        //这里改动一下, 获取列表中的数目
        //pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setPageSize(pageInfo.getList().size());

        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
