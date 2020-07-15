package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.order.OrderItem;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.mapper.order.OrderItemMapper;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController("/testmapper")
public class TestMapperController {
    @Autowired
    TaskFormMapper taskFormMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

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

        return taskFormMapper.getTaskForms();

    }
    @GetMapping("/test/getTaskForm")
    TaskForm getTaskForm(){
        TaskForm taskForm = taskFormMapper.getTaskForm(2020071301);
        System.out.println(taskForm.getSubSiteId());
        return taskFormMapper.getTaskForm(2020071301);
    }
    @GetMapping("/test/selectTaskFormBySubsiteId")
    List<TaskForm> getTaskFormByssid(){
        List<TaskForm> taskFormList = taskFormMapper.getTaskFormsBySubSiteId("SUB-S-002");
        System.out.println(taskFormList.size());
        return taskFormMapper.getTaskFormsBySubSiteId("SUB-S-002");
    }
    @GetMapping("/test/getTaskFormByOrderId")
    List<TaskForm> getTaskFormsByOrderId(){
        List<TaskForm> taskFormList1 = taskFormMapper.getTaskFormsByOrderId(10000000);
        System.out.println(taskFormList1.size());
        return taskFormMapper.getTaskFormsByOrderId(10000000);
    }
    @GetMapping("/test/searchTaskForms")
    List<TaskForm> searchTaskForms(){
        return taskFormMapper.searchTaskForms("娄底");
    }

    @GetMapping("/test/updateTaskFormStatus")
      TaskForm updateTaskFormStatus(){
        taskFormMapper.updateTaskFormStatus("W",2020071301);
        return taskFormMapper.getTaskForm(2020071301);
    }

    @GetMapping("/test/deleteTaskForm")
    List<TaskForm> deleteTaskForm(){
        //删除某一任务单时，设置orderItem的taskId为null
        OrderItem orderItem = orderItemMapper.getOrderItemByTaskId(2020071301);
        orderItem.setTaskId(null);
        orderItemMapper.updateOrderItemTaskId(orderItem);
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


}
