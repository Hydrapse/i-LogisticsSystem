package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.storage.AdjustForm;
import com.tcsquad.ilogistics.domain.storage.SubSite;
import com.tcsquad.ilogistics.mapper.order.OrderItemMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import com.tcsquad.ilogistics.mapper.storage.AdjustFormMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.mapper.storage.LogicInventoryMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController("/testmapper")
public class TestMapperController {
    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    LogicInventoryMapper logicInventoryMapper;

    @Autowired
    AdjustFormMapper adjustFormMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    SiteIOService siteIOService;

    @GetMapping("/test/insertTaskform")
    List<TaskForm> insertTaskForm() {
        BigDecimal doubleStr = new BigDecimal(Double.toString(99.9));
        Long taskid1 = new Long(2020071301);
        Long orderid1 = new Long(10000000);
        TaskForm taskForm1 = new TaskForm();
        taskForm1.setTaskId(taskid1);
        taskForm1.setOrderId(orderid1);
        taskForm1.setSubSiteId("SUB-S-002");
        taskForm1.setShipName("配送站SUB-S-002");
        taskForm1.setShipAddr("金渭路25号");
        taskForm1.setShipTel("15600988900");
        taskForm1.setShipPro("陕西省");
        taskForm1.setShipCity("宝鸡市");
        taskForm1.setShipDis("渭滨区");
        taskForm1.setBillName("姜美玉");
        taskForm1.setBillTel("15436780965");
        taskForm1.setBillPro("陕西省");
        taskForm1.setBillCity("西安市");
        taskForm1.setBillDis("未央区");
        taskForm1.setBillAddr("东风路灞河桥西");
        taskForm1.setStatus("Y");
        taskForm1.setCourier("周其之");
        taskForm1.setTotalPrice(doubleStr);
        taskForm1.setNote("");

        taskFormMapper.insertTaskForm(taskForm1);
        //taskFormMapper.insertTaskId();

        return taskFormMapper.getTaskForms();

    }
    @GetMapping("/test/getTaskForm")
    TaskForm getTaskForm(){
        TaskForm taskForm = taskFormMapper.getTaskForm(10000000);
        //System.out.println(taskForm.getSubSiteId());
        return taskFormMapper.getTaskForm(10000000);
    }
    @GetMapping("/test/selectTaskFormBySubsiteId")
    List<TaskForm> getTaskFormByssid(){
        List<TaskForm> taskFormList = taskFormMapper.getTaskFormsBySubSiteId("SUB-005");
        //System.out.println(taskFormList.size());
        return taskFormMapper.getTaskFormsBySubSiteId("SUB-005");
    }

    @GetMapping("/test/getTaskFormsByOrderId")
    List<TaskForm> getTaskFormsByOrderId(){
        List<TaskForm> taskFormList1 = taskFormMapper.getTaskFormsByOrderId(10000000);
        System.out.println(taskFormList1.size());
        return taskFormMapper.getTaskFormsByOrderId(10000000);
    }

    @GetMapping("/test/updateTaskFormStatus")
      TaskForm updateTaskFormStatus(){
        taskFormMapper.updateTaskFormStatus("U",10000000);
        return taskFormMapper.getTaskForm(10000000);
    }

    @GetMapping("/test/deleteTaskForm")
    List<TaskForm> deleteTaskForm(){
        //删除某一任务单时，设置orderItem的taskId为null
        List<OrderItem> orderItems = orderItemMapper.getOrderItemsByTaskId(2020071301);
        for(OrderItem orderItem:orderItems){
            orderItem.setTaskId(null);
            orderItemMapper.updateOrderItemTaskId(orderItem);
        }
        taskFormMapper.deleteTaskForm(2020071301);
        return taskFormMapper.getTaskForms();
    }

    @GetMapping("/test/deleteTaskFormsByOrderId")
    List<TaskForm> deleteTaskFormsByOrderId(){
        //删除某一任务单时，设置orderItem的taskId为null
        List<OrderItem> orderItemList = orderItemMapper.getOrderItemsByOrderId(10000000);
        for(int i=0; i<orderItemList.size();i++){
            orderItemList.get(i).setTaskId(null);
            orderItemMapper.updateOrderItemTaskId(orderItemList.get(i));
        }
        taskFormMapper.deleteTaskFormsByOrderId(10000000);
        return taskFormMapper.getTaskForms();
    }

    @GetMapping("/test/getTaskFormsByStatus")
    List<TaskForm> getTaskFormsByStatus(){
        return taskFormMapper.getTaskFormsByStatus("Y");
    }

    @GetMapping("/test/getTaskFormsBySubsiteAndStatus")
    List<TaskForm> getTaskFormsBySubsiteAndStatusAndKeyword(){
        //SubSite subSite = siteMapper.getSubSiteById("SUB-H-002");
        return taskFormMapper.getTaskFormsBySubsiteAndStatus("SUB-005","Y");
    }

    @GetMapping("/test/getTaskFormsBySubsiteAndKeyword")
    List<TaskForm> getTaskFormsBySubsiteAndKeyword(){
        //SubSite subSite = siteMapper.getSubSiteById("SUB-H-002");
        return taskFormMapper.getTaskFormsBySubsiteAndKeyword("SUB-005",new String[]{"2020070602"});
    }
    
    @GetMapping("/test/searchTaskForms")
    List<TaskForm> searchTaskForms(){
        String[] keyword = {"杨林","陕西省"};
        return taskFormMapper.searchTaskForms(keyword);
    }
    @GetMapping("/test/countTaskFormsByStatus")
    int countTaskFormsByStatus(){
        return taskFormMapper.countTaskFormsByStatus("Y");
    }


    @GetMapping("/test/testLogic")
    void getLogicInventory(String mainsiteId,String itemId,int logicInv){
        logicInventoryMapper.updateLogicInventory(mainsiteId,itemId,logicInv);
    }
    @GetMapping("/test/insertAdjustForm")
    List<AdjustForm> insertAdjustForm(){
        AdjustForm adjustForm = new AdjustForm();
        adjustForm.setAdjustId(10000000);
        adjustForm.setItemId("A-001");
        adjustForm.setItem(itemMapper.getItem("A-001"));
        adjustForm.setItemNum(5);
        adjustForm.setFromMainSiteId("MAIN-001");
        adjustForm.setToMainSiteId("MAIN-002");
        adjustForm.setAdjustStatus("N");
        adjustFormMapper.insertAdjustForm(adjustForm);
        return adjustFormMapper.getAdjustForms();
    }
    @GetMapping("/test/updateAdjustFormStatus")
    AdjustForm updateAdjustFormStatus(){
        AdjustForm adjustForm = adjustFormMapper.getAdjustForm(10000000);
        adjustForm.setAdjustStatus("T");
        adjustFormMapper.updateAdjustFormStatus(adjustForm);
        return adjustFormMapper.getAdjustForm(10000000);
    }
    @GetMapping("/test/updateAdjustFormRemainNum")
    AdjustForm updateAdjustFormRemainNum(){
        AdjustForm adjustForm = adjustFormMapper.getAdjustForm(10000000);
        adjustForm.setRemainNum(3);
        adjustFormMapper.updateAdjustFormRemainNum(adjustForm);
        return adjustFormMapper.getAdjustForm(10000000);
    }

    @GetMapping("/test/getAdjustFormsByFromId")
    List<AdjustForm> getAdjustFormsByFromId(){
        return adjustFormMapper.getAdjustFormsByFromId("Main-001");
    }

    @GetMapping("/test/getAdjustFormByItemId")
    List<AdjustForm> getAdjustFormByItemId(){
        return adjustFormMapper.getAdjustFormByItemId("A-001");
    }

    @PostMapping("/test/testSiteOut")
    public void insertSiteOutRecord(String type, Long formId, String mainsiteId){
        siteIOService.insertCheckOutRecord(StatusString.ADJUST_OUT.getValue(),Long.parseLong("10000003"),"MAIN-002");
    }




}
