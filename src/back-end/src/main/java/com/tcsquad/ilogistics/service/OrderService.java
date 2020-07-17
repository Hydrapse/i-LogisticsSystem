package com.tcsquad.ilogistics.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.order.ReturnForm;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.request.OrderAddReq;
import com.tcsquad.ilogistics.domain.request.OrderSelectReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.response.OrderBriefResp;
import com.tcsquad.ilogistics.domain.response.OrderDetailResp;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.domain.storage.MainSite;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.exception.NotFoundException;
import com.tcsquad.ilogistics.mapper.order.OrderItemMapper;
import com.tcsquad.ilogistics.mapper.order.OrderMapper;
import com.tcsquad.ilogistics.mapper.order.ReturnFormMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import com.tcsquad.ilogistics.settings.OrderSetting;
import com.tcsquad.ilogistics.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hydra
 * @date 2020/7/13
 * @description:
 */

@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    OrderSetting orderSetting; //订单管理模块配置类, 可控制订单审核逻辑

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    ReturnFormMapper returnFormMapper;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    AddressService addressService;

    /**
     * 功能描述: <br>
     * 完善OrderAddReq详细信息, 并且将OrderAddReq插入数据库
     */
    @Transactional
    public void insertOrderReq(OrderAddReq orderAddReq){
        //判断订单是否存在
        Long orderId = orderAddReq.getOrder().getOrderId();
        if (orderMapper.hasOrder(orderId)){
            throw new BusinessErrorException("新增订单错误, 订单 " + orderId + " 已经存在",
                    ErrorCode.ORDER_ALREADY_EXIST.getCode());
        }

        //若不存在插入订单
        Order order = orderAddReq.getOrder();
        List<OrderItem> orderItemList = orderAddReq.getOrderItemList();
        order.setLineNum(orderItemList.size()); //设置orderItem个数

        if(StringUtils.isEmpty(order.getProcessStatus())){
            order.setProcessStatus(StatusString.NOT_PROCESS.getValue());
        }
        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);

        //插入订单项
        for (OrderItem orderItem : orderItemList){
            Item item = itemMapper.getItem(orderItem.getItemId());
            orderItem.setItem(item);
            orderItem.setOrderId(orderId);
            orderItem.setStatus(StatusString.WAITING_DISTRIBUTION.getValue());
            orderItemMapper.insertOrderItem(orderItem);
        }

        logger.info("订单 " + orderId + " 已初始化");
    }

    /**
     * 功能描述:<br>
     * TODO: 订单预分拣, 返回主站信息
     */
    public MainSite preSlotForMainSite(Order order){
        var mainSites = siteMapper.getAllMainSite();
        var mainSitesPoints = new ArrayList<Pair<Double,Double>>();
        for(var mainSite:mainSites) {
            mainSitesPoints.add(Pair.of(mainSite.getLatitude().doubleValue(),mainSite.getLongitude().doubleValue()));
        }
        var destination = addressService.getPosition(order.getBillPro() + order.getBillCity() + order.getBillDistrict() + order.getBillAddr(), order.getBillCity());
        var destinationPoint = Pair.of(destination.getLat(),destination.getLng());
        int closest = addressService.closest(destinationPoint,mainSitesPoints);

        return mainSites.get(closest);
    }

    /**
     * 功能描述:<br>
     * 给消息队列服务器发送订单消息
     */
    public void sendOrderMessage(Order order){
        JSONObject orderMsg = new JSONObject();
        orderMsg.put("orderId", order.getOrderId());
        orderMsg.put("billName", order.getBillName());
        String dateString = dateFormat.format(order.getCreateDateTime()); //将日期格式化
        orderMsg.put("createDateTime", dateString);
        orderMsg.put("totalPrice", order.getTotalPrice());

        //向待审核订单队列发送消息unreviewed order
//        amqpTemplate.convertAndSend("unreviewed order", orderMsg.toJSONString());
        amqpTemplate.convertAndSend("unreviewed order", orderMsg.toJSONString());

        logger.info("成功发送订单消息: " + orderMsg.toJSONString());
    }

    /**
     * 功能描述:<br>
     * 获取审核单详情
     */
    public OrderDetailResp getDetailRespByOrderId(Long orderId){
        Order order = orderMapper.getOrder(orderId);
        if (order == null){
            throw new NotFoundException("orderId错误, 找不到对应订单", ErrorCode.ORDER_NOT_FOUND.getCode());
        }
        List<OrderItem> orderItemList = orderItemMapper.getOrderItemsByOrderId(orderId);
        MainSite mainsite  = preSlotForMainSite(order);

        //构造基本resp属性
        OrderDetailResp resp = new OrderDetailResp(order, orderItemList, mainsite);

        //TODO: msg 和 subsite 属性未加入

        return resp;
    }

    /**
     * 功能描述:<br>
     * 判断订单是否需要被审核
     */
    public boolean isCheckNeeded(OrderAddReq orderAddReq) {
        //判断是否启用配置
        if (orderSetting.isAvailable()){
            Order order = orderAddReq.getOrder();
            if (orderSetting.isTotalPriceLimit()){
                BigDecimal totalPrice = order.getTotalPrice();
                if (totalPrice.compareTo(orderSetting.getTotalPriceAmount()) == -1){
                    return false;
                }
            }
            if (orderSetting.isCategoryIdLimit()){
                for(OrderItem orderItem : orderAddReq.getOrderItemList()){
                   String categoryId = orderItem.getItem().getCategoryId();
                   if (orderSetting.getCategoryIdCheckList().contains(categoryId)){
                       return true; //如果有商品在待审核大类内, 则该订单需要审核
                   }
                }
                return false; //若没有商品在待审核大类内, 则无须审核
            }
        }

        //如果没有启用配置, 全部订单需要进行审核
        return true;
    }

    /**
     * 功能描述:<br>
     * 确认订单
     * @return 完整更新后的Order
     */
    @Transactional
    public Order confirmOrder(Long orderId, String processStatus, String shippingCost){
        Order order = orderMapper.getOrder(orderId);
        if (order == null){
            throw new NotFoundException("orderId错误, 找不到对应订单", ErrorCode.ORDER_NOT_FOUND.getCode());
        }

        //判断processStatus是否需要更新
        if (!StringUtils.isEmpty(processStatus)){
            if (StatusString.isInGroup(processStatus, StatusString.Group.OrderProcess)){
                order.setProcessStatus(processStatus);
                orderMapper.updateProcessStatus(order);
            }
            else{ //未知订单进度状态码
                throw new BusinessErrorException("订单processStatus错误: " + processStatus,
                        ErrorCode.PARAMS_ERROR.getCode());
            }
        }
        //判断ShippingCost是否需要更新
        if (!StringUtils.isEmpty(shippingCost)){
            double cost;
            try {
                cost = Double.valueOf(shippingCost);
            } catch ( NumberFormatException e) {
                throw new BusinessErrorException("运费错误,无法转换成double值: " + shippingCost,
                        ErrorCode.PARAMS_ERROR.getCode());
            }
            order.setShippingCost(cost);
            orderMapper.updateShippingCost(order);
        }

        return order;
    }

    /**
     * 功能描述:<br>
     * 检查mainsiteId是否正确
     */
    public void checkMainSiteId(String mainsiteId){
        if (!siteMapper.hasMainSiteId(mainsiteId)){
            throw new BusinessErrorException("mainsiteId不存在, 请重试", ErrorCode.PARAMS_ERROR.getCode());
        }
    }

    /**
     * 功能描述:<br>
     * 更新订单处理状态
     */
    public void updateProcessStatus(Order order){
        orderMapper.updateProcessStatus(order);
    }

    public PageResult selectOrders(OrderSelectReq orderSelectReq, PageRequest pageRequest) {
        //如果dateTo为空, 则填入当前时间
        String dateTo = orderSelectReq.getDateTo();
        if(StringUtils.isEmpty(dateTo)){
            dateTo = dateFormat.format(new Date());
            orderSelectReq.setDateTo(dateTo);
        }

        //分页设置
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        //必须紧挨着查询语句
        PageHelper.startPage(pageNum, pageSize);
        List<OrderBriefResp> respList = orderMapper.getOrderBriefsByReq(orderSelectReq);

        return PageUtil.getPageResult(pageRequest, new PageInfo<>(respList));

    }

    /**
     * 功能描述:<br>
     * 获取订单信息
     */
    public Order getOrderById(Long orderId){
        return orderMapper.getOrder(orderId);
    }

    /**
     * 功能描述:<br>
     * 获取订单项商品列表
     */
    public List getOrderItemListByOrderId(Long orderId){
        return orderItemMapper.getOrderItemsByOrderId(orderId);
    }

    public List getTaskformListByOrderId(Long orderId){
        List<TaskForm> taskFormList = taskFormMapper.getTaskFormsByOrderId(orderId);
        if(taskFormList == null || taskFormList.isEmpty()){
            return null;
        }

        for (TaskForm taskForm:taskFormList){
            List<OrderItem> orderItems = taskFormMapper.getTaskItemsByTaskId(taskForm.getTaskId());
            taskForm.setOrderItems(orderItems);
        }

        return taskFormList;
    }

    public List getReturnformListByOrderId(Long orderId){
        List<ReturnForm> forms = new ArrayList<>();
        List<ReturnForm> returnForms = returnFormMapper.getReturnFormByOrderId(orderId);
        for (ReturnForm r:returnForms){
            forms.add(r);
        }
        List<ReturnForm> changeForms = returnFormMapper.getChangeFormByOrderId(orderId);
        for (ReturnForm c:changeForms){
            forms.add(c);
        }

        return forms;
    }
}
