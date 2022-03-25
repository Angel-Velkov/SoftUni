package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity AS o ORDER BY o.price DESC")
    List<OrderEntity> findAllOrderedByPriceDesc();
}
