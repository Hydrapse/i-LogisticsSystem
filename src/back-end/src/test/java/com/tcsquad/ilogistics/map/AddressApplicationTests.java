package com.tcsquad.ilogistics.map;

import com.tcsquad.ilogistics.ILogisticsSystemApplication;
import com.tcsquad.ilogistics.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.List;

@SpringBootTest(classes = ILogisticsSystemApplication.class)
public class AddressApplicationTests {
    @Autowired
    AddressService addressService;

    @Test
    void test() {
//        System.out.println(addressService.distance(Pair.of(40.45,116.34), List.of(Pair.of(40.54,116.35))));
        System.out.println(addressService.getPosition("北京市海淀区上地十街10号","北京市"));
    }
}
