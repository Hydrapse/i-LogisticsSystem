package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.clientele.Customer;
import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.request.CustomerReq;
import com.tcsquad.ilogistics.domain.request.SupplierAddReq;
import com.tcsquad.ilogistics.domain.response.ItemSupplyResp;
import com.tcsquad.ilogistics.service.ClienteleService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteleController {
    private static Logger logger = LoggerFactory.getLogger(ClienteleController.class);

    @Autowired
    ClienteleService clienteleService;

    //查询买家列表
    @GetMapping("/clientele/customers")
    public List<Customer> getCustomerList(){
        return clienteleService.getCustomers();
    }


    //未完成
    @GetMapping("")
    public List<Customer> customerList(CustomerReq customerReq){

        List<Customer> customerList = clienteleService.getCustomerListByCustomerReq(customerReq);
        if(customerList.isEmpty()){
            logger.info("不存在匹配顾客");
            return null;
        }
        else
            return customerList;
    }
    /*
    @GetMapping("/clientele/{orderId}/customer")
    public Customer getCustomersByOrderId(@PathVariable("orderId")long orderId){
        Customer customer = clienteleService.getCustomerByOrderId(orderId);
        if(customer != null){
            return customer;
        }
        else{
            logger.info("未找到订单编号为 "+orderId+" 的顾客");
            return null;
        }

    }



    @GetMapping("/clientele/customer/addr={addr}")
    public List<Customer> getCustomersByAddr(@PathVariable("addr")String addr){
        List<Customer> customerList = clienteleService.getCustomersByAddr(addr);
        if(!customerList.isEmpty()){
            return customerList;
        }
        else{
            logger.info("未找到地址为 "+addr+" 的顾客");
            return null;
        }

    }

    @GetMapping("/clientele/customer/keyword={keyword}")
    public List<Customer> getCustomersByKeyword(@PathVariable("keyword")String keyword){
        List<Customer> customerList = clienteleService.getCustomersByKeyword(keyword);
        if(!customerList.isEmpty()){
            return customerList;
        }
        else{
            logger.info("未找到信息包含 "+keyword+" 的顾客");
            return null;
        }

    }*/

    @PostMapping("/suppliers")
    public void newSupplier(@RequestBody SupplierAddReq supplierAddReq){
        //新增供应商
        clienteleService.insertSupplierReq(supplierAddReq);
    }
    //获取供应商列表
    @GetMapping("/clientele/suppliers")
    public List<Supplier> getSupplierList(){
        return clienteleService.getSupplierList();
    }
    //查看某一供应商商品供应列表
    @GetMapping("/clientele/suppliers/{supplierId}/itemSupply")
    public ItemSupplyResp getItemSupplyResp(@PathVariable("supplierId")String supplierId){
        ItemSupplyResp itemSupplyResp = clienteleService.getItemSupplyRespBySupplierId(supplierId);
        return itemSupplyResp;
    }
    //删除供应商
    @DeleteMapping("/clientele/suppliers/{supplierId}")
    public List<Supplier> deleteSupplier(@PathVariable("supplierId")String supplierId){
        Supplier supplier = clienteleService.getSupplierById(supplierId);
        clienteleService.deleteSupplier(supplier);
        return clienteleService.getSupplierList();

    }

    @PatchMapping("/clientele/supplier/{supplierId}")
    public Supplier updateSupplier(@PathVariable("supplierId")String supplierId,String managerName,String tel,String email){
        Supplier supplier = clienteleService.getSupplierById(supplierId);
        if(managerName != ""){
            supplier.setManagerName(managerName);
        }
        if(tel != ""){
            supplier.setTel(tel);
        }
        if(email != ""){
            supplier.setEmail(email);
        }
        clienteleService.updateSupplier(supplier);
        return clienteleService.getSupplierById(supplier.getSupplierId());
    }





}
