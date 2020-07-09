package com.tcsquad.ilogistics.mapper;

import com.tcsquad.ilogistics.domain.delivery.Courier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierMapper {
    List<Courier> getCouriersBySubsiteId(String subsiteId);

    void insertCourier(Courier courier);

    void updateCourier(Courier courier);

    void deleteCourierByCourierId(String courierId);

}
