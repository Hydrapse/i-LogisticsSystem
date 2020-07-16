package com.tcsquad.ilogistics.mapper.clientele;

import com.tcsquad.ilogistics.domain.clientele.Customer;
import com.tcsquad.ilogistics.domain.request.CustomerReq;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {
    List<Customer> getCustomers();//买家列表

    List<Customer> getCustomersByCustomerReq(CustomerReq customerReq);

    List<Customer> getCustomersByName(String name);

    Customer getCustomerByOrderId(long orderId);

    List<Customer> getCustomersByKeyword(String keyword);

    List<Customer> getCustomersByAddr(String addr);

    Customer getCustomerByCustomerId(long customerId);//单买家详细信息查询

    void updateCustomer(Customer customer);
}
