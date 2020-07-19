package com.tcsquad.ilogistics.settings;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SiteIOSetting extends GeneralSetting{
    //是否启用入库单总金额 判断策略, 默认为true
    //入库单总金额大于等于多少时需要进行审核, 默认为0
    private boolean totalPriceLimit;
    private BigDecimal totalPriceAmount;

    //是否启用入库数量 判断策略，默认为true
    //入库数量大于等于多少时需要进行审核，默认为0
    private boolean totalNumLimit;
    private Integer totalNum;

    //是否启用大类审核白名单, 默认为false
    //若启用, 指定CategoryId不需要进行审核
    private boolean categoryIdLimit;
    private List<String> categoryIdWhiteList;

    //是否启用入库类型审核白名单，默认为false
    //若启用，指定类型的入库单不需要进行审核
    private boolean siteInTypeLimit;
    private List<String> siteInTypeWhiteList;

    //是否启用出库类型审核白名单，默认为false
    //若启用，指定类型的出库单不需要进行审核
    private boolean siteOutTypeLimit;
    private List<String> siteOutTypeWhiteList;

    //默认值
    public SiteIOSetting() {
        totalPriceLimit = true;
        totalPriceAmount = new BigDecimal(0);
        totalNumLimit = true;
        totalNum = 0;
        categoryIdLimit = false;
        categoryIdWhiteList = new ArrayList<>();
        siteInTypeLimit = false;
        siteInTypeWhiteList = new ArrayList<>();
        siteOutTypeLimit = false;
        siteOutTypeWhiteList = new ArrayList<>();
    }

    public boolean isInCategoryIdWhiteList(String cid){
        for(String categoryId:categoryIdWhiteList){
            if(categoryId.equals(cid))
                return true;
        }

        return false;
    }

    public boolean isInSiteInTypeWhiteList(String type){
        for(String t:siteInTypeWhiteList){
            if(t.equals(type))
                return true;
        }

        return false;
    }

    public boolean isInSiteOutTypeWhiteList(String type){
        for(String t:siteOutTypeWhiteList){
            if(t.equals(type))
                return true;
        }
        return false;
    }

    public boolean isTotalPriceLimit() {
        return totalPriceLimit;
    }

    public void setTotalPriceLimit(boolean totalPriceLimit) {
        this.totalPriceLimit = totalPriceLimit;
    }

    public BigDecimal getTotalPriceAmount() {
        return totalPriceAmount;
    }

    public void setTotalPriceAmount(BigDecimal totalPriceAmount) {
        this.totalPriceAmount = totalPriceAmount;
    }

    public boolean isTotalNumLimit() {
        return totalNumLimit;
    }

    public void setTotalNumLimit(boolean totalNumLimit) {
        this.totalNumLimit = totalNumLimit;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public boolean isCategoryIdLimit() {
        return categoryIdLimit;
    }

    public void setCategoryIdLimit(boolean categoryIdLimit) {
        this.categoryIdLimit = categoryIdLimit;
    }

    public boolean isSiteInTypeLimit() {
        return siteInTypeLimit;
    }

    public void setSiteInTypeLimit(boolean siteInTypeLimit) {
        this.siteInTypeLimit = siteInTypeLimit;
    }

    public boolean isSiteOutTypeLimit() {
        return siteOutTypeLimit;
    }

    public void setSiteOutTypeLimit(boolean siteOutTypeLimit) {
        this.siteOutTypeLimit = siteOutTypeLimit;
    }

    public List<String> getCategoryIdWhiteList() {
        return categoryIdWhiteList;
    }

    public void setCategoryIdWhiteList(List<String> categoryIdWhiteList) {
        this.categoryIdWhiteList = categoryIdWhiteList;
    }

    public List<String> getSiteInTypeWhiteList() {
        return siteInTypeWhiteList;
    }

    public void setSiteInTypeWhiteList(List<String> siteInTypeWhiteList) {
        this.siteInTypeWhiteList = siteInTypeWhiteList;
    }

    public List<String> getSiteOutTypeWhiteList() {
        return siteOutTypeWhiteList;
    }

    public void setSiteOutTypeWhiteList(List<String> siteOutTypeWhiteList) {
        this.siteOutTypeWhiteList = siteOutTypeWhiteList;
    }
}
