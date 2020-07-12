package com.tcsquad.ilogistics.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

/**
 * @Description 提供地址信息验证,地图等服务
 **/
@Service
public class AddressService {

    Pair<Double,Double> getPosition(String address){
        return null;
    }

    double distance(Pair<Double,Double> x,Pair<Double,Double> y){
        return 0;
    }
}
