package com.example.xmlcardealer.service;

import com.example.xmlcardealer.model.dto.SupplierSeedDto;
import com.example.xmlcardealer.model.entity.Supplier;

import java.util.List;

public interface SupplierService {

    void seedSuppliers(List<SupplierSeedDto> suppliers);

    Supplier getRandomSupplier();
}
