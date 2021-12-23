package com.example.xmlproductshop.service;

import com.example.xmlproductshop.model.dto.ProductSeedDto;

import java.util.List;

public interface ProductService {

    void seedProducts(List<ProductSeedDto> products);
}
