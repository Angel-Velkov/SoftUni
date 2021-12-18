package com.example.cardealer.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @OneToOne
    private Car car;

    @OneToOne
    private Customer customer;

    @Column(name = "discount")
    private double discountPercentage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return id != null && Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
