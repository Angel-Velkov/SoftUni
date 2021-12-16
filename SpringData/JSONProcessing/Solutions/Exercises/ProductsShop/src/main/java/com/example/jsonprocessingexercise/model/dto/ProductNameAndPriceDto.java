package com.example.jsonprocessingexercise.model.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductNameAndPriceDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String seller;
}
