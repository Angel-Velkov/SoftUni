package com.example.cardealer.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    private String name;

    private BigDecimal price;

    private int quantity;

    @ManyToOne
    private Supplier supplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Part part = (Part) o;
        return id != null && Objects.equals(id, part.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
