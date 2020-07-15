package com.tcsquad.ilogistics.domain;

/**
 * @author Hydra
 * @date 2020/6/16
 * @description: 分页查询结果封装类。
 */

import lombok.Data;

import java.util.List;

@Data
public class PageResult {

    //当前页码
    private int pageNum;

    //每页数量
    private int pageSize;

    //记录总数
    private long totalSize;

    //页码总数
    private int totalPages;

    //数据列表
    private List<?> content;

}