package com.example.xmlproductshop.init;

import com.example.xmlproductshop.model.dto.CategorySeedRootDto;
import com.example.xmlproductshop.model.dto.ProductSeedRootDto;
import com.example.xmlproductshop.model.dto.UserSeedRootDto;
import com.example.xmlproductshop.service.CategoryService;
import com.example.xmlproductshop.service.ProductService;
import com.example.xmlproductshop.service.UserService;
import com.example.xmlproductshop.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String RESOURCES_FILES_PATH = "src/main/resources/files/";
    private static final String CATEGORIES_FILE_NAME = "categories.xml";
    private static final String USERS_FILE_NAME = "users.xml";
    private static final String PRODUCTS_FILE_NAME = "products.xml";

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final XmlParser xmlParser;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService,
                                 ProductService productService, XmlParser xmlParser) {

        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedData();
    }

    private void seedData() throws JAXBException {
        CategorySeedRootDto categorySeedRootDto =
                xmlParser.fromFile(
                        RESOURCES_FILES_PATH + CATEGORIES_FILE_NAME, CategorySeedRootDto.class
                );

        categoryService.seedCategories(categorySeedRootDto.getCategories());

        UserSeedRootDto userSeedRootDto =
                xmlParser.fromFile(
                        RESOURCES_FILES_PATH + USERS_FILE_NAME, UserSeedRootDto.class
                );

        userService.seedUsers(userSeedRootDto.getUsers());

        ProductSeedRootDto productSeedRootDto = xmlParser.fromFile(
                RESOURCES_FILES_PATH + PRODUCTS_FILE_NAME, ProductSeedRootDto.class
        );

        productService.seedProducts(productSeedRootDto.getProducts());
    }
}
