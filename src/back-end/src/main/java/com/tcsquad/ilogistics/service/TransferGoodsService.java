package com.tcsquad.ilogistics.service;

import com.alibaba.fastjson.JSON;
import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.storage.AdjustForm;
import com.tcsquad.ilogistics.domain.storage.MainSite;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.AdjustFormMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import com.tcsquad.ilogistics.settings.TransferSetting;
import com.tcsquad.ilogistics.util.IDSequenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Autowired
    TransferSetting transferSetting;

    @Autowired
    AddressService addressService;

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
        if(remain*transferSetting.getOutStockRatio()/100.0 >= require) {
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

            //DONE: 主站间距离排序, 从最近主站开始遍历
            var mainSites = siteMapper.getAllMainSite();
            mainSites.removeIf(mainSite -> {return mainSite.getMainsiteId().equals(lackGoodsMainSiteId);});
            var mainSitesIds = mainSites.stream().map(MainSite::getMainsiteId).collect(Collectors.toList());

            //TODO: 调货数量策略

            if(transferSetting.getTransferSite().equals("D")) { //距离排序
                var startSite = siteMapper.getMainSiteById(lackGoodsMainSiteId);
                var startSitePoint = Pair.of(startSite.getLatitude().doubleValue(),startSite.getLongitude().doubleValue());
                var mainSitesPoints = mainSites.stream().map(mainSite -> {
                    return Pair.of(mainSite.getLatitude().doubleValue(),mainSite.getLongitude().doubleValue());
                }).collect(Collectors.toList());
                var distanceResult = addressService.distance(startSitePoint,mainSitesPoints).stream().map(result -> {
                    if(transferSetting.isByTime())
                        return result.getDuration().getValue();
                    else
                        return result.getDistance().getValue();
                }).collect(Collectors.toList());

                var mainSitesIdsMap = new HashMap<String,Integer>();
                for(int i= 0;i<mainSites.size();i++)
                    mainSitesIdsMap.put(mainSites.get(i).getMainsiteId(),i);

                mainSitesIds.stream().sorted((o1, o2) -> { //近的在前
                    double t1 = distanceResult.get(mainSitesIdsMap.get(o1));
                    double t2 = distanceResult.get(mainSitesIdsMap.get(o2));
                    if(t1<t2)
                        return -1;
                    else if(t1 == t2)
                        return 0;
                    else
                        return 1;
                }).collect(Collectors.toList());
            } else { //数量排序
                var mainSitesNum = mainSites.stream().map(mainSite -> {
                    return logicalInventoryService.logicInventoryMapper.getLogicInventoryByMainsiteAndItemId(mainSite.getMainsiteId(),orderItem.getItemId());
                }).collect(Collectors.toList());

                var mainSitesIdsMap = new HashMap<String,Integer>();
                for(int i= 0;i<mainSites.size();i++)
                    mainSitesIdsMap.put(mainSites.get(i).getMainsiteId(),i);

                mainSitesIds.stream().sorted((o1, o2) -> {
                    return mainSitesNum.get(mainSitesIdsMap.get(o2)) - mainSitesNum.get(mainSitesIdsMap.get(o1)); //大的在前
                }).sorted();
            }

            //DONE?: 配置调度货物数量策略
            var num = Math.max(require,transferSetting.getTransferNum());
//            var num = require * 2;

            //从特定的一个主站调取商品
            for(var mainSiteId: mainSitesIds) {
                //不遍历原主站 :在定义mainSites时添加数了
//                if (mainSite.getMainsiteId().equals(lackGoodsMainSiteId))
//                    continue;

                //TODO: 没有判断当一批大订单来临, 没有一个主站能单独供给时的情况
                //判断并减少库存
                if(logicalInventoryService.decreaseLogicInventory
                        (mainSiteId,orderItem.getItemId(),num)) {
                    //生成任务单
                    var adjustForm = new AdjustForm();
                    adjustForm.setItem(orderItem.getItem());
                    adjustForm.setItemNum(num);
                    //adjustForm.setRemainNum();
                    adjustForm.setAdjustStatus(StatusString.A_UNPROCESSED.getValue());
                    adjustForm.setAdjustId(idSequenceUtil.getNextFormIdByName(SequenceName.ADJUST_FORM.getValue()));
                    adjustForm.setFromMainSiteId(lackGoodsMainSiteId);
                    adjustForm.setToMainSiteId(mainSiteId);
                    adjustForm.setItemId(orderItem.getItemId());
                    //插入任务单
                    adjustFormMapper.insertAdjustForm(adjustForm);

                    //出库
                    siteIOService.insertCheckOutRecord
                            (StatusString.ADJUST_OUT.getValue(),adjustForm.getAdjustId(),mainSiteId);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
//        var tmp = List.of("1","2","3"); //ERROR
        var tmp = new ArrayList<String>(List.of("1","2","3"));
        tmp.removeIf(t->t.equals("3"));
        System.out.println(tmp);
    }
}
