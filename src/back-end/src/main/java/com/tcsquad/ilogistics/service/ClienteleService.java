package com.tcsquad.ilogistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.clientele.Customer;
import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.request.CustomerReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.request.SupplierAddReq;
import com.tcsquad.ilogistics.domain.response.ItemSupplyResp;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.mapper.clientele.CustomerMapper;
import com.tcsquad.ilogistics.mapper.clientele.SupplierMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class
ClienteleService {
    private static Logger logger = LoggerFactory.getLogger(ClienteleService.class);

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    ItemMapper itemMapper;

    public PageResult getCustomers(CustomerReq customerReq,PageRequest pageRequest){
        checkPageRequest(pageRequest);
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Customer> customerList = customerMapper.getCustomersByCustomerReq(customerReq);
        return PageUtil.getPageResult(pageRequest, new PageInfo<>(customerList));

    }

    public List<Customer> getCustomerByName(String name){
        return customerMapper.getCustomersByName(name);
    }
    public Customer getCustomerByOrderId(long orderId){
        return customerMapper.getCustomerByOrderId(orderId);
    }

    public List<Customer> getCustomersByKeyword(String keyword){
        return customerMapper.getCustomersByKeyword(keyword);
    }
    public List<Customer> getCustomersByAddr(String addr){
        return  customerMapper.getCustomersByAddr(addr);
    }

    public Customer getCustomerById(long customerId){
        return customerMapper.getCustomerByCustomerId(customerId);
    }

    public List<Customer> getCustomerListByCustomerReq(CustomerReq customerReq){
        return customerMapper.getCustomersByCustomerReq(customerReq);
    }

    @Transactional
    public void insertSupplierReq(SupplierAddReq supplierAddReq){
        //判断供应商是否存在
        String supplierId = supplierAddReq.getSupplier().getSupplierId();
        if (supplierMapper.hasSupplier(supplierId)){
            throw new BusinessErrorException("新增供应商错误, 供应商 " + supplierId + " 已经存在",
                    ErrorCode.SUPPLIER_ALREADY_EXIST.getCode());
        }

        //若不存在插入供应商
        Supplier supplier = supplierAddReq.getSupplier();
        List<String> itemSupplyList = supplierAddReq.getItemSupplyList();

        supplierMapper.insertSupplier(supplier);

        //插入商品供应信息
        for (String itemid : itemSupplyList){
            Item item = itemMapper.getItem(itemid);
            item.setStatus(StatusString.ITEM_SUPPLY_NORMAL.getValue());
            supplierMapper.insertItemSupply(supplierId,item);
        }

        logger.info("供应商 " + supplierId + " 已添加");
    }

    public void deleteSupplier(Supplier supplier){
        supplierMapper.deleteSupplier(supplier.getSupplierId());
        System.out.println(supplierMapper.hasSupplier(supplier.getSupplierId()));
    }

    public void updateSupplier(Supplier supplier){
        supplierMapper.updateSupplier(supplier);
    }

    public PageResult getSupplierList(PageRequest pageRequest) {
        checkPageRequest(pageRequest);
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Supplier> supplierList = supplierMapper.getSuppliers();
        return PageUtil.getPageResult(pageRequest, new PageInfo<>(supplierList));
    }

    public Supplier getSupplierById(String supplierId){
        return supplierMapper.getSupplierBySupplierId(supplierId);
    }

    //获取商品供应列表
    public ItemSupplyResp getItemSupplyRespBySupplierId(String supplierId){
        ItemSupplyResp itemSupplyResp = new ItemSupplyResp();
        if(supplierMapper.hasSupplier(supplierId)){
            itemSupplyResp.setSupplierId(supplierId);
            List<Item> itemSupplyList = new ArrayList<>();
            List<String> itemIdList = supplierMapper.getItemSupplyListBySupplierId(supplierId);
            for(int i=0; i<itemIdList.size();i++){
                itemSupplyList.add(itemMapper.getItem(itemIdList.get(i)));
            }
            itemSupplyResp.setItemSupplyList(itemSupplyList);
        }
        return itemSupplyResp;

    }

    private void checkPageRequest(PageRequest pageRequest) {
        if(pageRequest == null || pageRequest.getPageNum()== null || pageRequest.getPageSize() == null)
            throw new BusinessErrorException("分页信息不能为空",ErrorCode.PARAMS_ERROR.getCode());
    }

}
