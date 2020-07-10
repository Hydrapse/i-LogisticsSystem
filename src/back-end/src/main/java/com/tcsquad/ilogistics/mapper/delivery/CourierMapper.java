package com.tcsquad.ilogistics.mapper.delivery;

import com.tcsquad.ilogistics.domain.delivery.Courier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierMapper {
    List<Courier> getCouriersBySubsiteId(String subsiteId);

    Courier getCourierByCourierId(long courierId);

    void insertCourier(Courier courier);

    void updateCourier(Courier courier);

    void deleteCourierByCourierId(long courierId);

}
