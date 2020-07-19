package com.tcsquad.ilogistics.settings;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/13
 * @description: 订单配置类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class OrderSetting extends GeneralSetting{

    //是否启用订单总金额 判断策略, 默认为true
    //订单总金额大于等于多少时需要进行审核, 默认为0
    private boolean totalPriceLimit;
    private BigDecimal totalPriceAmount;

    //是否启用大类审核白名单, 默认为false
    //若启用, 指定CategoryId不需要进行审核
    private boolean categoryIdLimit;
    private List<String> categoryIdWhiteList;

    //是否启用顾客白名单, 默认为false
    //若启用, 指定customerId不需要进行审核
    private boolean customerIdLimit;
    private List<String> customerIdWhiteList;

    //默认值
    public OrderSetting() {
        totalPriceLimit = true;
        totalPriceAmount = new BigDecimal(0);
        categoryIdLimit = false;
        categoryIdWhiteList = new ArrayList<>();
        customerIdLimit=false;
        customerIdWhiteList=new ArrayList<>();
    }

    public boolean isInCategoryIdWhiteList(String cid){
        for(String categoryId:categoryIdWhiteList){
            if(categoryId.equals(cid))
                return true;
        }
        return false;
    }

    public boolean isInCustomerIdWhiteList(String cid){
        for(String customerId:customerIdWhiteList){
            if(customerId.equals(cid))
                return true;
        }
        return false;
    }
}
