package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column(nullable = false)
    private Integer neededTime;
}
