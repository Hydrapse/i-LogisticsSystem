package com.tcsquad.ilogistics.domain;


public enum StatusString {

    /**
     * 订单progressStatus说明:
     * 未处理（N 意为not process）
     * 正在处理（P 意为processing）
     * 交易成功（D 意为done）
     * 交易关闭（C 意为close）
     */
    NOT_PROCESS("N", Group.OrderProcess),
    PROCESSING("P", Group.OrderProcess),
    ORDER_DONE("D", Group.OrderProcess),
    ORDER_CLOSE("C", Group.OrderProcess),

    /**
     * OrderItem status 处理状态说明
     * 缺货（O 意为stockOut）已处理
     * 已分配给任务单(P)
     * 待分配任务单（W waiting）
     */
    ITEM_STOCK_OUT("O", Group.OrderItem),
    ITEM_PREPARED("P", Group.OrderItem),
    WAITING_DISTRIBUTION("W", Group.OrderItem),


    /**
     * 主站库房商品出入库记录表(SiteIO)的type值说明
     * 补货入库（IN-01）/调货入库（IN-02）/退货入库（IN-03）；
     * 退货给供应商（OUT-01）/调货出库（OUT-02）/发货出库（OUT-03）
     */
    SUPPLY_IN("IN-01", Group.SiteIOType),

    ADJUST_IN("IN-02", Group.SiteIOType),

    RETURN_IN("IN-03", Group.SiteIOType),

    CHANGE_IN("IN-04", Group.SiteIOType),

    SUPPLY_OUT("OUT-01", Group.SiteIOType),

    ADJUST_OUT("OUT-02", Group.SiteIOType),

    SHIP_OUT("OUT-03", Group.SiteIOType),

    /**
     * ItemSupply status 商品供应状态说明
     * 正常供应（N NORMAL)
     * 停止供应（E END)
     */
    ITEM_SUPPLY_NORMAL("N", Group.ItemSupply),
    ITEM_SUPPLY_END("E", Group.ItemSupply),


    /**
     * 主站库房商品出入库记录表(SiteIO)的approvalStatus值说明
     * 待审核（W）waiting /已确认（Y）confirm /已失效（F）invalid
     */
    WAITING("W", Group.SiteIOApproval),

    CONFIRM("Y", Group.SiteIOApproval),

    INVALID("F", Group.SiteIOApproval),

    /**
     * 任务单的status值说明
     * 缺货等待调货（W）waiting，未发出（U）unsent，运输中（O）on the way，未配送（N）not delivered，已签收（Y）accepted
     */
    T_WAITING("W",Group.TaskFormProcess),
    T_UNSENT("U",Group.TaskFormProcess),
    T_ON_THE_WAY("O",Group.TaskFormProcess),
    T_NOT_DELIVERED("N",Group.TaskFormProcess),
    T_ACCEPTED("Y",Group.TaskFormProcess),
    ;

    private String value;
    private Group group;

    StatusString(String str, Group group){
        this.value = str;
        this.group = group;
    }

    public String getValue() {
        return value;
    }

    /**
     * 功能描述:<br>
     * 判断某个状态码是否为指定组状态
     */
    public static boolean isInGroup(String value, Group group) {
        boolean include = false;
        for (StatusString e: StatusString.values()){
            if(e.value.equals(value) && e.group == group){
                include = true;
                break;
            }
        }
        return include;
    }

    public enum Group{
        OrderProcess,
        OrderItem,
        SiteIOType,
        SiteIOApproval,
        ItemSupply,
        TaskFormProcess
    }
}
