package com.tcsquad.ilogistics.service;

import com.alibaba.fastjson.JSON;
import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.storage.AdjustForm;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.AdjustFormMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import com.tcsquad.ilogistics.util.IDSequenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferGoodsService {
    private Logger logger = LoggerFactory.getLogger(TransferGoodsService.class);

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

    @Autowired
    SiteIOService siteIOService;

    /**
     * 生成调货信息
     * @param lackGoodsMainSiteId 被调入主站
     * @param taskForm 任务单
     */
    @Transactional
    void generateTransfer(String lackGoodsMainSiteId, TaskForm  taskForm) {
        logger.info(lackGoodsMainSiteId + " 开始生成调货单, 对应任务单号为 " + taskForm.getTaskId());

        var orderItem = taskForm.getOrderItems().get(0);
        var adjustforms = adjustFormMapper.getAdjustFormsByFromId(lackGoodsMainSiteId);
        int remain = 0 ;
        int require = orderItem.getItemNum();
        for(var adjustform:adjustforms) {
            if(adjustform.getItemId().equals(orderItem.getItemId())
                    && !adjustform.getAdjustStatus().equals(StatusString.A_REACH.getValue())) {
                remain += adjustform.getRemainNum();
            }
        }

        //所有调货单可用数量可供补货
        if(remain >= require) {
            logger.debug("当前调货单可用数量可供补货, 无须新增调货单");
            for(int index=0; require > 0; ++index) {
                if(adjustforms.get(index).getItemId().equals(orderItem.getItemId())
                        && !adjustforms.get(index).getAdjustStatus().equals(StatusString.A_REACH.getValue()))
                {
                    int tmp = Math.min(require,adjustforms.get(index).getRemainNum());
                    require -= tmp;
                    adjustforms.get(index).setRemainNum(adjustforms.get(index).getRemainNum() - tmp);

                    logger.info("更新调货单" + adjustforms.get(index).getAdjustId() +
                            " 剩余可用数目: " + adjustforms.get(index).getRemainNum());
                    adjustFormMapper.updateAdjustFormRemainNum(adjustforms.get(index));
                }
            }
        }
        //需要新增调货单
        else {
            logger.debug("当前调货单可用数量不足, 新增调货单");

            //TODO: 主站间距离排序, 从最近主站开始遍历
            var mainSites = siteMapper.getAllMainSite();

            //TODO: 配置调度货物数量策略
            var num = require * 2;

            //从特定的一个主站调取商品
            for(var mainSite: mainSites) {
                //不遍历原主站
                if (mainSite.getMainsiteId().equals(lackGoodsMainSiteId))
                    continue;

                //TODO: 没有判断当一批大订单来临, 没有一个主站能单独供给时的情况
                //判断并减少库存
                if(logicalInventoryService.decreaseLogicInventory
                        (mainSite.getMainsiteId(),orderItem.getItemId(),num)) {
                    //生成任务单
                    var adjustForm = new AdjustForm();
                    adjustForm.setItem(orderItem.getItem());
                    adjustForm.setItemNum(num);
                    //adjustForm.setRemainNum();
                    adjustForm.setAdjustStatus(StatusString.A_UNPROCESSED.getValue());
                    adjustForm.setAdjustId(idSequenceUtil.getNextFormIdByName(SequenceName.ADJUST_FORM.getValue()));
                    adjustForm.setFromMainSiteId(lackGoodsMainSiteId);
                    adjustForm.setToMainSiteId(mainSite.getMainsiteId());
                    adjustForm.setItemId(orderItem.getItemId());
                    //插入任务单
                    adjustFormMapper.insertAdjustForm(adjustForm);

                    //出库
                    siteIOService.insertCheckOutRecord
                            (StatusString.ADJUST_OUT.getValue(),adjustForm.getAdjustId(),mainSite.getMainsiteId());
                    break;
                }
            }
        }
    }
}
