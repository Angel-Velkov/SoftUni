package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);

    List<Ingredient> findAllByNameStartingWith(String prefix);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> ingredientNames);

    @Modifying
    @Transactional
    int deleteIngredientByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient AS i SET i.price = i.price * :percentage WHERE i.name IN :name")
    int increaseThePriceByNames(@Param("names") Iterable<String> names,
                                @Param("percentage") BigDecimal percentage);
}
