package com.tcsquad.ilogistics.mapper.clientele;

import com.tcsquad.ilogistics.domain.clientele.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {
    List<Customer> getCustomers();//买家列表

    Customer getCustomerByCustomerId(long customerId);//单买家详细信息查询

    void updateCustomer(Customer customer);
}
