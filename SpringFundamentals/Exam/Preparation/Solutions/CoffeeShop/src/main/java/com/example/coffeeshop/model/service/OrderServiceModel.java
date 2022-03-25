package com.example.coffeeshop.model.service;

import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OrderServiceModel {
    private String name;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private CategoryNameEnum category;
    private String description;
}
