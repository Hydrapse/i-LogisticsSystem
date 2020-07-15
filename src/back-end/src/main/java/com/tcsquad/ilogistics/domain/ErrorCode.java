package com.tcsquad.ilogistics.domain;

/**
 * 错误代码枚举类
 */
public enum ErrorCode {
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(40401),

    /**
     * 订单不存在
     */
    ORDER_NOT_FOUND(40402),

    /**
     * 订单已经提交
     */
    ORDER_ALREADY_SUBMIT(40001),

    /**
     * 缺少参数
     */
    MISS_PARAMS(40002),

    /**
     * 参数错误
     */
    PARAMS_ERROR(40003),

    /**
     * 订单已经存在
     */
    ORDER_ALREADY_EXIST(40004),

    /**
     * 订单已经存在
     */
    SUPPLIER_ALREADY_EXIST(40005),

    /**
     *用户权限不足, 无法调用接口
     */
    USER_FORBIDDEN_ACCESS(40301),

    /**
     *消息队列服务器宕机
     */
    MESSAGE_QUEUE_DOWNTIME(50001),

    /**
     * 连接错误
     */
    CONNECTION_ERROR(50002),

    /**
     * 外部服务器错误
     */
    OUTSIDE_SERVER_ERROR(50003),
    ;

    private int code;

    public int getCode()
    {
        return code;
    }

    ErrorCode(int code)
    {
        this.code = code;
    }
}
