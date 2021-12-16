package com.example.jsonprocessingexercise.model.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategorySeedDto {
    @Expose
    @Size(min = 3, max = 15)
    private String name;
}
