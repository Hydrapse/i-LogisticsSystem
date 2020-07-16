package com.tcsquad.ilogistics.service.interf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.util.RedisUtil;
import com.tcsquad.ilogistics.util.StockOutMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/16
 * @description: 缺货处理业务层
 */

@Service
public class StockOutService {
    private static Logger logger = LoggerFactory.getLogger(StockOutService.class);

    @Autowired
    StockOutMsgUtil stockOutMsgUtil;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 功能描述:<br>
     * 所有货物入站都需要调用这个函数, 会首先判断有无缺货消息,若没有则跳出循环, 否则处理缺货消息
     * @param mainsiteId
     * @param itemId
     * @param qty 货物入站数量
     * @return 返回处理完缺货后的剩余数量
     */
    @Transactional
    public int handleStockOut(String mainsiteId, String itemId, int qty){
        String key = stockOutMsgUtil.keyConcat(mainsiteId, itemId);

        //TODO: qty应该包含本次入库数量加现有库存数量(逻辑库存)

        //缺货消息处理循环
        boolean flag = qty > 0; //控制是否继续循环
        while(flag && redisUtil.hasKey(key)){
            //缺货消息列表
            List list = stockOutMsgUtil.consumeStockOutMessage(key);
            int listSize = list.size();

            //消息列表的下标, 指向下一个要处理的元素
            int index = 0;

            for(Object msg : list){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(msg);
                long orderId = (long) jsonObject.get("oid");
                long taskId = (long) jsonObject.get("tid");
                int itemNum = (int) jsonObject.get("num");

                if(qty >= itemNum){ //库存足够处理当前缺货消息
                    qty -= itemNum;
                    ++index; //移动下标

                    //TODO: 处理任务单taskId的发货任务
                    logger.info("处理任务单 " + taskId + " 的发货任务");
                    //...
                }
                else break; //库存不足
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

        return qty;
    }

}
