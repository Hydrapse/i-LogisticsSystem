package com.tcsquad.ilogistics.settings;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class TransferSetting {
    @Getter@Setter
    private boolean byTime;
    @Getter
    private int outStockRatio;
    @Getter@Setter
    private int transferNum;
    @Getter
    private String transferSite;

    public TransferSetting() {
        this.byTime = false;
        this.outStockRatio = 100;
        this.transferNum = 0;
        this.transferSite = "D";
    }

    public void setOutStockRatio(int outStockRatio) {
        if(outStockRatio < 50)
            outStockRatio = 50;
        else if(outStockRatio > 100)
            outStockRatio = 100;
        this.outStockRatio = outStockRatio;
    }

    public void setTransferSiteByDistance() {
        this.transferSite = "D";
    }

    public void setTransferSiteByNum() {
        this.transferSite = "N";
    }
}
