package com.example.pathfinder.repository;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Set<CategoryEntity> findAllByNameIn(Collection<CategoryNameEnum> name);
}
