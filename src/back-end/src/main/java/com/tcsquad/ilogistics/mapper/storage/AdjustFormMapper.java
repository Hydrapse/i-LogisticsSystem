package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.AdjustForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdjustFormMapper {
    void insertAdjustForm(AdjustForm adjustForm);

    List<AdjustForm> getAdjustForms();

    //通过发送调货请求的主站编号查询调货单列表
    List<AdjustForm> getAdjustFormsByFromId(String fromMainSiteId);

    //通过调取货物的编号查询调货单列表
    List<AdjustForm> getAdjustFormByItemId(String itemId);

    //更新可供缺货数量
    void updateAdjustFormRemainNum(AdjustForm adjustForm);

    AdjustForm getAdjustForm(long adjustId);

    void updateAdjustFormStatus(AdjustForm adjustForm);


}
