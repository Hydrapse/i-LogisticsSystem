package com.tcsquad.ilogistics.domain.request;

import lombok.Data;

/**
 * @author Hydra
 * @date 2020/6/19
 * @description:
 */

@Data
public class OrderSelectReq {

    private Long orderId;

    private String billName;

    private String dateFrom = "";

    private String dateTo;

    private String processStatus;
}
