package com.example.nextleveltechnologies.repository;

import com.example.nextleveltechnologies.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsAllBy();
}
