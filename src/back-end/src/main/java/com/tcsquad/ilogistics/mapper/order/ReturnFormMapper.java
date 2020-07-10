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
    ReturnForm getReturnFormByReturnId(int returnId);
    //查询某一换货单详细记录
    ReturnForm getChangeFormByReturnId(int returnId);

    void insertReturnForm(ReturnForm returnForm);

    void updateReturnFormStatus(String status, int returnId);

    void deleteReturnForm(int returnId);
}
