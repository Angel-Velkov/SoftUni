package com.example.jsonprocessingexercise.init;

import com.example.jsonprocessingexercise.model.dto.ProductNameAndPriceDto;
import com.example.jsonprocessingexercise.service.CategoryService;
import com.example.jsonprocessingexercise.service.ProductService;
import com.example.jsonprocessingexercise.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String OUTPUT_PATH = "src/main/resources/files/out/";
    public static final String PRODUCTS_IN_RANGE = "products-in-range.json";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Gson gson;
    private final BufferedReader reader;

    @Autowired
    public CommandLineRunnerImpl(UserService userService, CategoryService categoryService, ProductService productService, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.gson = gson;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedData();

        this.productsInRange();

//        int exercise = Integer.parseInt(reader.readLine());
//
//        switch (exercise) {
//            case 1:
//                this.productsInRange();
//        }
    }

    private void seedData() throws IOException {
        this.userService.seedUsers();
        this.categoryService.seedCategories();
        this.productService.seedProducts();
    }

    private void productsInRange() throws IOException {
        List<ProductNameAndPriceDto> productDtos = this.productService
                .productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String json = gson.toJson(productDtos);

        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE, json);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(
                Path.of(filePath),
                Collections.singleton(content)
        );
    }
}
