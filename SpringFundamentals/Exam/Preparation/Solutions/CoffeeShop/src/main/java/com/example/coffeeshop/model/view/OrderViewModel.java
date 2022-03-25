package com.example.coffeeshop.model.view;

import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class OrderViewModel {
    private Long id;
    private String name;
    private BigDecimal Price;
    private CategoryNameEnum category;
}
