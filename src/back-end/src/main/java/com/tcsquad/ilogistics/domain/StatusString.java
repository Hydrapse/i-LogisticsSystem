package com.tcsquad.ilogistics.domain;


public enum StatusString {

    /**
     * 主站库房商品出入库记录表的type值说明
     * 补货入库（IN-01）/调货入库（IN-02）/退货入库（IN-03）；
     * 退货给供应商（OUT-01）/调货出库（OUT-02）/发货出库（OUT-03）
     */
    SUPPLY_IN("IN-01"),

    ADJUST_IN("IN-02"),

    RETURN_IN("IN-03"),

    SUPPLY_OUT("OUT-01"),

    ADJUST_OUT("OUT-02"),

    SHIP_OUT("OUT-03"),

    /**
     * 主站库房商品出入库记录表的approvalStatus值说明
     * 待审核（W）waiting /已确认（C）confirm /已失效（I）invalid
     */
    WAITING("W"),

    CONFIRM("C"),

    INVALID("I");
    ;

    String value;
    public String getValue() {
        return value;
    }

    StatusString(String str){
        this.value=str;
    }
}
