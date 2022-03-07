package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
