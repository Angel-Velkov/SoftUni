package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.view.EmployeeViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String userName);

    @Query("SELECT " +
            "new com.example.coffeeshop.model.view.EmployeeViewModel(u.username, SIZE(u.orders)) " +
            "FROM UserEntity AS u " +
            "WHERE SIZE(u.orders) > 0 " +
            "ORDER BY SIZE(u.orders) DESC")
    List<EmployeeViewModel> findAllEmployees();
}
