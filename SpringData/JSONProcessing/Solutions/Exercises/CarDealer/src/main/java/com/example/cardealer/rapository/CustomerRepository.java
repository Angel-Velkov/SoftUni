package com.example.cardealer.rapository;

import com.example.cardealer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer AS c " +
            "ORDER BY c.birthDate, c.isYoungDriver DESC")
    List<Customer> findAllOrderByBirthDateAscThenByNotYoungDriver();
}
