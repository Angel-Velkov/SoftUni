package com.example.coffeeshop.service.init;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum name) {
        return this.categoryRepository.findByName(name).orElse(null);
    }
}
