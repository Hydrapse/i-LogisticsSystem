package com.tcsquad.ilogistics.domain.request;

import lombok.Data;

@Data
public class TransferSettingReq {
    private Boolean byTime;
    private Integer outStockRatio;
    private Integer transferNum;
    private String transferSite;
}
