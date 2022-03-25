package com.example.coffeeshop.model.binding;

import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OrderAddBindingModel {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;

    @NotNull
    private CategoryNameEnum category;

    @NotBlank
    @Size(min = 5)
    private String description;
}
