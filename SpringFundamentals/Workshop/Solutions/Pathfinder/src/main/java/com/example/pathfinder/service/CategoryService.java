package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.enums.CategoryNameEnum;

import java.util.Collection;
import java.util.Set;

public interface CategoryService {

    Set<CategoryEntity> findAllCategoriesWithNameIn(Collection<CategoryNameEnum> names);
}
