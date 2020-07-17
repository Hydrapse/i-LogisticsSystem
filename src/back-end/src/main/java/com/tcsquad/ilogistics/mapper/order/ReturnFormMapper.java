package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.ReturnForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnFormMapper {
    //退货单列表
    List<ReturnForm> getReturnForms();

    //换货单列表
    List<ReturnForm> getChangeForms();

    //查询某一退货单详细记录
    ReturnForm getReturnFormByFormId(long formId);

    //根据订单号查询退货单
    List<ReturnForm> getReturnFormByOrderId(long orderId);

    //查询某一换货单详细记录
    ReturnForm getChangeFormByFormId(long formId);
    //根据订单号查询换货单
    List<ReturnForm> getChangeFormByOrderId(long orderId);

    void insertReturnForm(ReturnForm returnForm);

    void updateReturnFormStatus(String status, long formId);

    void deleteReturnForm(long formId);
}
