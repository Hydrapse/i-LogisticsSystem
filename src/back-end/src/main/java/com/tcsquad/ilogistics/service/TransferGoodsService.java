package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.storage.AdjustForm;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.AdjustFormMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import com.tcsquad.ilogistics.util.IDSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferGoodsService {

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    AdjustFormMapper adjustFormMapper;

    @Autowired
    IDSequenceUtil idSequenceUtil;

    @Autowired
    LogicalInventoryService logicalInventoryService;

    /**
     * 生成调货信息
     * @param mainSiteId 被调入主站
     * @param taskForm 任务单
     */
    @Transactional
    void generateTransfer(String mainSiteId, TaskForm  taskForm) {
        var orderItem = taskForm.getOrderItems().get(0);
        var adjustforms = adjustFormMapper.getAdjustFormsByFromId(mainSiteId);
        int remain = 0 ;
        int require = orderItem.getItemNum();
        for(var adjustform:adjustforms) {
            if(adjustform.getItemId().equals(orderItem.getItemId()))
                remain += adjustform.getRemainNum();
        }

        if(remain >= require) { //所有调货单可用数量可供补货
            int index = 0;
            while(require > 0) {
                if(adjustforms.get(index).getItemId().equals(orderItem.getItemId())) {
                    int tmp = Math.min(require,adjustforms.get(index).getRemainNum());
                    require -= tmp;
                    adjustforms.get(index).setItemNum(adjustforms.get(index).getRemainNum() - tmp);
                    //adjustFormMapper.updateAdjustForm();//todo
                }
                index ++;
            }
        } else { //需要新增调货单
            var mainSites = siteMapper.getAllMainSite();
            var num = require * 2;
            for(var mainSite: mainSites) {
                if(logicalInventoryService.decreaseLogicInventory(mainSite.getMainsiteId(),orderItem.getItemId(),num)) { //判断并减少库存
                    var adjustForm = new AdjustForm();
                    adjustForm.setItem(orderItem.getItem());
                    adjustForm.setItemNum(num);
//                    adjustForm.setRemainNum();
                    adjustForm.setAdjustStatus(StatusString.A_UNPROCESSED.getValue());
                    adjustForm.setAdjustId(idSequenceUtil.getNextFormIdByName(SequenceName.ADJUST_FORM.getValue()));
                    adjustForm.setToMainSiteId(mainSite.getMainsiteId());
                    adjustForm.setFromMainSiteId(mainSiteId);
                    adjustForm.setItemId(orderItem.getItemId());
                    adjustFormMapper.insertAdjustForm(adjustForm);
                    break;
                }
            }
        }
    }
}
