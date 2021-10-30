package com.example.advquerying.init;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AppInitializer implements CommandLineRunner {

    private final ShampooRepository shampooRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public AppInitializer(ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Select Shampoos by Size
        /*this.shampooRepository.findAllBySizeOrderById(Size.MEDIUM)
                .forEach(this::printShampoo);*/

        // 2. Select Shampoos by Size or Label
        /*this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(Size.MEDIUM, 10)
                .forEach(this::printShampoo);*/

        // 3. Select Shampoos by Price
        /*this.shampooRepository.findAllByPriceAfterOrderByPriceDesc(new BigDecimal("5"))
                .forEach(this::printShampoo);*/

        // 4. Select Ingredients by Name
        /*this.ingredientRepository.findAllByNameStartingWith("M")
                .forEach(this::printIngredient);*/

        // 5. Select Ingredients by Names
        /*List<String> ingredientNames = List.of("Lavender", "Herbs", "Apple");

        this.ingredientRepository.findAllByNameInOrderByPrice(ingredientNames)
               .forEach(this::printIngredient);*/

        // 6. Count Shampoos by Price
        /*System.out.println("count: " +
                this.shampooRepository.countByPriceBefore(new BigDecimal("8.50"))
        );*/
    }

    private void printShampoo(Shampoo shampoo) {
        System.out.printf("%3d | %-50.50s | %slv.%n",
                shampoo.getId(),
                shampoo.getBrand(),
                shampoo.getPrice());
    }

    private void printIngredient(Ingredient ingredient) {
        System.out.printf("%3d | %-20.20s | %slv.%n",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getPrice());
    }
}
