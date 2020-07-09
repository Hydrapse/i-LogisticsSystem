package com.tcsquad.ilogistics.mapper;

import com.tcsquad.ilogistics.domain.clientele.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerMapper {
    List<Customer> getCustomers();//买家列表

    Customer getCustomer();//单买家详细信息查询

    void updateCustomer(Customer customer);
}
