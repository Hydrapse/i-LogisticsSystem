package com.tcsquad.ilogistics.settings;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/13
 * @description: 订单配置类
 */

@Component
public class OrderSetting extends GeneralSetting{

    //是否启用订单总金额 判断策略, 默认为true
    //订单总金额大于等于多少时需要进行审核, 默认为0
    private boolean totalPriceLimit;
    private BigDecimal totalPriceAmount;

    //是否启用大类审核限制, 默认为false
    //若启用, 指定CategoryId需要进行审核
    private boolean categoryIdLimit;
    private List<String> categoryIdCheckList;

    //默认值
    public OrderSetting() {
        totalPriceLimit = true;
        totalPriceAmount = new BigDecimal(0);
        categoryIdLimit = false;
        categoryIdCheckList = new ArrayList<>();
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

    public boolean isCategoryIdLimit() {
        return categoryIdLimit;
    }

    public void setCategoryIdLimit(boolean categoryIdLimit) {
        this.categoryIdLimit = categoryIdLimit;
    }

    public List<String> getCategoryIdCheckList() {
        return categoryIdCheckList;
    }

    public void setCategoryIdCheckList(List<String> categoryIdCheckList) {
        this.categoryIdCheckList = categoryIdCheckList;
    }
}
