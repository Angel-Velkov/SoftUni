package com.example.cardealer.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;

    private boolean isImporter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return id != null && Objects.equals(id, supplier.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
