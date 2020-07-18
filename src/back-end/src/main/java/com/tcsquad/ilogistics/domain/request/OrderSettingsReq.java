package com.tcsquad.ilogistics.domain.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderSettingsReq {
    Boolean enable;
    Boolean totalPriceLimit;
    BigDecimal totalPriceAmount;
    Boolean categoryIdLimit;
    List<String> categoryIdWhiteList;
    Boolean customerIdLimit;
    List<String> customerIdWhiteList;
}
