package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<CategoryEntity> findAllCategoriesWithNameIn(Collection<CategoryNameEnum> names) {
        return this.categoryRepository.findAllByNameIn(names);
    }
}
