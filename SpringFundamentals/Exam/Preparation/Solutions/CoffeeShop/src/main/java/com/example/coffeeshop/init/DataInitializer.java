package com.example.coffeeshop.init;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.coffeeshop.model.entity.enums.CategoryNameEnum.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() == 0) {
            List<CategoryEntity> categories = List.of(
                    new CategoryEntity(COFFEE, COFFEE.getNeededTime()),
                    new CategoryEntity(CAKE, CAKE.getNeededTime()),
                    new CategoryEntity(DRINK, DRINK.getNeededTime()),
                    new CategoryEntity(OTHER, OTHER.getNeededTime())
            );

            this.categoryRepository.saveAll(categories);
        }
    }
}
