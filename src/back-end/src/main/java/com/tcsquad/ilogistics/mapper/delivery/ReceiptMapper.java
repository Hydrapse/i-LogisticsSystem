package com.tcsquad.ilogistics.mapper.delivery;

import com.tcsquad.ilogistics.domain.delivery.Receipt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptMapper {
    void insertReceipt(Receipt receipt);

    List<Receipt> getReceiptsByResponsible(String responsible);

    List<Receipt> getReceiptsBytaskId(long taskId);
}
