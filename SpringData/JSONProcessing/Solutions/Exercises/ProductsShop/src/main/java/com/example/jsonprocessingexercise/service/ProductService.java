package com.example.jsonprocessingexercise.service;

import com.example.jsonprocessingexercise.model.dto.ProductNameAndPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException;

    List<ProductNameAndPriceDto> productsInRange(BigDecimal lower, BigDecimal upper);
}
