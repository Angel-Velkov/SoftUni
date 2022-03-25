package com.example.coffeeshop.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @ManyToOne(optional = false)
    private CategoryEntity category;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(optional = false)
    private UserEntity employee;
}
