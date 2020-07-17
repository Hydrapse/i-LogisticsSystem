package com.tcsquad.ilogistics.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.mapper.storage.LogicInventoryMapper;
import com.tcsquad.ilogistics.util.RedisUtil;
import com.tcsquad.ilogistics.util.StockOutMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/16
 * @description: 逻辑库存业务层, 负责处理逻辑库存的更新
 */

@Service
public class LogicalInventoryService {
    private static Logger logger = LoggerFactory.getLogger(LogicalInventoryService.class);

    @Autowired
    StockOutMsgUtil stockOutMsgUtil;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LogicInventoryMapper logicInventoryMapper;

    /**
     * 前置条件:<br>
     *     WarehouseService中确认入库消息, 真实库存与逻辑库存增加完毕, 进入缺货信息处理阶段
     * 功能描述:<br>
     *     所有货物入站都需要调用这个函数, 会首先判断有无缺货消息,若没有则跳出循环, 否则处理缺货消息
     * 事务描述:<br>
     *     把当前存在的(调用该方法的)事务挂起, 创建一个新事务, 如果发生回滚则缺货消息未完成, 但不会影响
     *     源真实库存与逻辑库存的更新
     * @param mainsiteId
     * @param itemId
     * @return 返回处理完缺货后的剩余数量
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleIncrease(String mainsiteId, String itemId, int increment){
        String key = stockOutMsgUtil.keyConcat(mainsiteId, itemId); //Redis索引键

        if(!redisUtil.hasKey(key) ) { //如果不含缺货队列, 则直接退出
            logger.info("键 " + key + " 不存在缺货信息");
            return;
        }
        else if (increment <= 0){
            logger.info("increment小于等于0, 退出缺货消息处理");
        }

        //qty为当前逻辑库存数量
        int qty = logicInventoryMapper.getLogicInventoryByMainsiteAndItemId(mainsiteId, itemId);
        qty += increment; //原逻辑库存加上现有入库数

        //缺货消息处理循环
        boolean flag = qty > 0; //控制是否继续循环
        while(flag && redisUtil.hasKey(key)){
            //缺货消息列表
            List list = stockOutMsgUtil.consumeStockOutMessage(key);
            int listSize = list.size();
            if (listSize < stockOutMsgUtil.getMsgThroughput()){
                logger.info("键 " + key + " 所有缺货消息预计可被消费完毕");
                flag = false;
            }

            //消息列表的下标, 指向下一个要处理的元素
            int index = 0;

            for(Object msg : list){
                JSONObject jsonObject = JSON.parseObject(msg.toString());
                long orderId = (int) jsonObject.get("oid");
                long taskId = (int) jsonObject.get("tid");
                int itemNum = (int) jsonObject.get("num");

                if(qty >= itemNum){ //库存足够处理当前缺货消息
                    logger.info("消费缺货消息" + msg.toString());
                    qty -= itemNum;
                    ++index; //移动下标

                    //TODO: 处理任务单taskId的发货任务
                    logger.info("处理任务单 " + taskId + " 的发货任务");
                }
                else {
                    logger.info("缺货消息需要库存数大于现有库存数, 该缺货消息未被处理: " + msg.toString());
                    break; //库存不足
                }
            }

            //将已处理的缺货消息存入缓存, 并设置过期时间
            stockOutMsgUtil.storeSettledMsg(key, list.subList(0, index));

            //如果货物库存不足以处理完本次缺货消息列表, 则退出循环
            if(index < listSize){
                //将未处理的缺货消息返回原队列, 并置于顶部
                stockOutMsgUtil.rowbackStockOutMessage(key, list.subList(index, listSize));
                flag = false;
            }
        }

        //更新逻辑库存
        logicInventoryMapper.updateLogicInventory(mainsiteId, itemId, qty);
    }

    /**
     * 功能描述:<br>
     * @param increment 扣除逻辑库存数量
     * @return 若扣除成功, 返回true; 若库存不足, 返回false
     */
    @Transactional
    public boolean decreaseLogicInventory(String mainsiteId, String itemId, int increment){
        int qty = logicInventoryMapper.getLogicInventoryByMainsiteAndItemId(mainsiteId, itemId);

        //库存充足
        if (increment < qty){
            qty -= increment;
            logicInventoryMapper.updateLogicInventory(mainsiteId, itemId, qty);
            return true;
        }
        //库存不足
        return false;
    }
}
