package com.tcsquad.ilogistics.util;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/16
 * @description: 缺货信息处理Redis工具类
 */
@Component
public class StockOutMsgUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH");

    @Autowired
    RedisUtil redisUtil;

    /**
     * 缺货信息吞吐量
     * 一次取出的缓存信息
     */
    private long msgThroughput = 100;

    /**
     * 已处理缺货消息过期时间, 单位: 秒
     * 默认 7天
     */
    private long expiryTime = 604800;

    public long getMsgThroughput() {
        return msgThroughput;
    }

    public void setMsgThroughput(long msgThroughput) {
        this.msgThroughput = msgThroughput;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    /**
     * 功能描述:<br>
     * 根据mainsiteId 和 itemId 拼接成 Redis Key
     */
    public String keyConcat(String mainsiteId, String itemId){
        String key = mainsiteId + "|" + itemId;
        return key;
    }

    /**
     * 功能描述:<br>
     * 将缺货消息存入缓存
     * @param mainsiteId 主站id
     * @param orderItem 缺货订单项
     */
    public boolean insertStockOutMessage(String mainsiteId, OrderItem orderItem){
        JSONObject obj = new JSONObject();
        obj.put("oid", orderItem.getOrderId());
        obj.put("tid", orderItem.getTaskId());
        obj.put("num", orderItem.getItemNum());

        //ListId: MAIN-001|A-001
        String listKey = keyConcat(mainsiteId, orderItem.getItemId());
        boolean rtn = redisUtil.lSet(listKey, obj.toJSONString()); //如果没有对应key, list会自动创建

        return rtn;
    }

    /**
     * 消费对应缺货消息
     * @param listKey 指定主站货物缺货信息列表的Key
     * @return 缺货消息列表
     */
    public List consumeStockOutMessage(String listKey){
        List rtnList;
        long maxSize = redisUtil.lGetListSize(listKey);

        if (maxSize < msgThroughput){
            rtnList = redisUtil.lGet(listKey, 0, maxSize-1);
            redisUtil.del(listKey);
        }
        else {
            rtnList = redisUtil.lGet(listKey, 0, msgThroughput-1);
            redisUtil.lRemove(listKey, msgThroughput);
        }
//        //存储已处理消息, 并设置过期时间
//        storeSettledMsg(listKey, rtnList);
        return rtnList;
    }

    /**
     * 功能描述:<br>
     * 将那些没消费完的缺货信息, 回滚回缓存, 并且置于队列顶部
     */
    public boolean rowbackStockOutMessage(String listKey, List msgList){
        if(msgList == null || msgList.size() == 0){
            return false;
        }
        boolean rtn = redisUtil.lSetLeft(listKey, msgList);
        return rtn;
    }

    /**
     * 功能描述:<br>
     * 将已处理消息存入缓存, 并设置过期时间
     * key格式为: MAIN-001|A-001|2020-07-16:11
     */
    public void storeSettledMsg(String originKey, List msgList){
        if(msgList == null || msgList.size() == 0) {
            return;
        }
        //已信息存储Key: MAIN-001|A-001|2020-07-16:11
        String newKey = originKey + "|" + dateFormat.format(new Date());

        //存入Redis中, 并设置过期时间
        redisUtil.lSet(newKey, msgList, expiryTime);
    }

}
