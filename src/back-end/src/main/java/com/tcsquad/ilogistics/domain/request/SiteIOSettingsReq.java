package com.tcsquad.ilogistics.domain.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SiteIOSettingsReq {
    Boolean enable;
    Boolean totalPriceLimit;
    BigDecimal totalPriceAmount;
    Boolean totalNumLimit;
    Integer totalNum;
    Boolean categoryIdLimit;
    List<String> categoryIdWhiteList;
    Boolean siteInTypeLimit;
    List<String> siteInTypeWhiteList;
    Boolean siteOutTypeLimit;
    List<String> siteOutTypeWhiteList;
}
