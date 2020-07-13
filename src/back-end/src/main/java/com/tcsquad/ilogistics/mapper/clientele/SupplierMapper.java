package com.tcsquad.ilogistics.mapper.clientele;

import com.tcsquad.ilogistics.domain.clientele.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMapper {
    List<Supplier> getSuppliers();

    Supplier getSupplierBySupplierId(String supplierId);

    void insertSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(String supplierId);
}
