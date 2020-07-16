package com.tcsquad.ilogistics.mapper.clientele;

import com.tcsquad.ilogistics.domain.clientele.Supplier;
import com.tcsquad.ilogistics.domain.response.ItemSupplyResp;
import com.tcsquad.ilogistics.domain.storage.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMapper {
    List<Supplier> getSuppliers();

    Supplier getSupplierBySupplierId(String supplierId);

    void insertSupplier(Supplier supplier);

    void insertItemSupply(String supplierId, Item item);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(String supplierId);

    boolean hasSupplier(String supplierId);

    List<String> getItemSupplyListBySupplierId(String supplierId);
}
