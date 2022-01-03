package com.example.nextleveltechnologies.repository;

import com.example.nextleveltechnologies.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsAllBy();
}
