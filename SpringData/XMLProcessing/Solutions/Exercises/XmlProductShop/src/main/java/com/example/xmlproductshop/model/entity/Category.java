package com.example.xmlproductshop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @NonNull
    @Column(nullable = false, length = 15)
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
}
