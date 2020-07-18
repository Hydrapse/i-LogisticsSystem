package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.clientele.Customer;
import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.request.CustomerReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.request.SupplierAddReq;
import com.tcsquad.ilogistics.domain.response.ItemSupplyResp;
import com.tcsquad.ilogistics.service.ClienteleService;
import io.swagger.annotations.ApiOperation;
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

    //根据限定条件获取买家列表
    @GetMapping("/clientele/customers")
    public PageResult getCustomerList(CustomerReq customerReq,PageRequest pageRequest){
        return clienteleService.getCustomers(customerReq,pageRequest);
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

    }*/

    @PostMapping("/suppliers")
    public void newSupplier(@RequestBody SupplierAddReq supplierAddReq){
        //新增供应商
        clienteleService.insertSupplierReq(supplierAddReq);
    }
    //获取供应商列表
    @GetMapping("/clientele/suppliers")
    public PageResult getSupplierList(PageRequest pageRequest){
        return clienteleService.getSupplierList(pageRequest);
    }
    //查看某一供应商商品供应列表
    @GetMapping("/clientele/suppliers/{supplierId}/itemSupply")
    public ItemSupplyResp getItemSupplyResp(@PathVariable("supplierId")String supplierId){
        ItemSupplyResp itemSupplyResp = clienteleService.getItemSupplyRespBySupplierId(supplierId);
        return itemSupplyResp;
    }
    //删除供应商
    @DeleteMapping("/clientele/suppliers/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId")String supplierId){
        Supplier supplier = clienteleService.getSupplierById(supplierId);
        clienteleService.deleteSupplier(supplier);

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

    @ApiOperation("customerId自动补全")
    @GetMapping("/clientele/customerIds")
    public List getCustomerIdsByInfix(String infix){
        return clienteleService.getCustomerIdsByInfix(infix);
    }



}
