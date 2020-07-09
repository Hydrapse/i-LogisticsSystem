package com.tcsquad.ilogistics.mapper;

import com.tcsquad.ilogistics.domain.order.ReturnForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnFormMapper {
    List<ReturnForm> getReturnForms();

    ReturnForm getReturnForm(int returnId);

    void insertReturnForm(ReturnForm returnForm);

    void updateReturnFormStatus(String status, int returnId);

    void deleteReturnForm(int returnId);
}
