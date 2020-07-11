package com.tcsquad.ilogistics.domain.request;

/**
 * @author Hydra
 * @date 2020/6/16
 * @description: 分页查询请求封装类
 */

public class PageRequest {
    //当前页码
    private Integer pageNum;

    //每页数量
    private Integer pageSize;

    /**
     * 功能描述: <br>
     * <判断原本pageRequest是否完整初始化, 若无, 则用入参初始化, 否则不变>
     */
    public void initialValidate(Integer pageNum, Integer pageSize){
        if(this.pageNum == null) this.pageNum = pageNum;

        if(this.pageSize == null) this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}