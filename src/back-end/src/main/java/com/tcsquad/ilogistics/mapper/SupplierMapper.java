package com.tcsquad.ilogistics.mapper;

import com.tcsquad.ilogistics.domain.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMapper {
    List<Supplier> getSuppliers();

    void insertSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(String supplierId);
}
