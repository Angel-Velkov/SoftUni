package com.example.advquerying.init;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class AppInitializer implements CommandLineRunner {

    private final IngredientService ingredientService;
    private final ShampooRepository shampooRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public AppInitializer(IngredientService ingredientService, ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.ingredientService = ingredientService;
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Select Shampoos by Size
       /* this.shampooRepository.findAllBySizeOrderById(Size.MEDIUM)
                .forEach(this::printShampoo);*/

        // 2. Select Shampoos by Size or Label
        /*this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(Size.MEDIUM, 10)
                .forEach(this::printShampoo);
*/
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

        //TODO
        // 7. Select Shampoos by Ingredients
        /*List<String> ingredientNames = List.of("Berry", "Mineral-Collagen");

        this.shampooRepository.findAllByIngredientNames(ingredientNames)
                .forEach(this::printShampoo);*/

        // 8. Select Shampoos by Ingredients Count
        /*this.shampooRepository.findAllByIngredientsCountBefore(2)
                .forEach(this::printShampoo);*/

        // 9. Delete Ingredients by Name
        /*String nameToDelete = "Apple";

        System.out.println("Number of deleted ingredients is: " +
                this.ingredientService.deleteIngredientByName(nameToDelete)
        );*/

        // 11. Update Ingredients by Names
        /*System.out.println("Number of updated ingredients is: " +
                this.ingredientRepository
                        .increaseThePriceByName(Set.of("Apple", "Lavender"), new BigDecimal("0.10"))
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
