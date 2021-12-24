package com.example.xmlcardealer.init;

import com.example.xmlcardealer.model.dto.CarsSeedRootDto;
import com.example.xmlcardealer.model.dto.CustomersSeedRootDto;
import com.example.xmlcardealer.model.dto.PartsSeedRootDto;
import com.example.xmlcardealer.model.dto.SuppliersSeedRootDto;
import com.example.xmlcardealer.service.*;
import com.example.xmlcardealer.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String RESOURCES_FILES_PATH = "src/main/resources/files/";
    private static final String CARS_FILE_NAME = "cars.xml";
    private static final String CUSTOMERS_FILE_NAME = "customers.xml";
    private static final String PARTS_FILE_NAME = "parts.xml";
    private static final String SUPPLIERS_FILE_NAME = "suppliers.xml";

    private static final int COUNT_OF_SALES = 20;

    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;
    private final SupplierService supplierService;
    private final XmlParser xmlParser;

    @Autowired
    public CommandLineRunnerImpl(CarService carService, CustomerService customerService,
                                 PartService partService, SaleService saleService,
                                 SupplierService supplierService, XmlParser xmlParser) {

        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedData();
    }

    private void seedData() throws JAXBException {
        // Suppliers seeding
        SuppliersSeedRootDto suppliersSeedRootDto =
                xmlParser.fromFile(
                        RESOURCES_FILES_PATH + SUPPLIERS_FILE_NAME, SuppliersSeedRootDto.class
                );

        supplierService.seedSuppliers(suppliersSeedRootDto.getSuppliers());

        // Parts seeding
        PartsSeedRootDto partsSeedRootDto = xmlParser.fromFile(
                RESOURCES_FILES_PATH + PARTS_FILE_NAME, PartsSeedRootDto.class
        );

        partService.seedParts(partsSeedRootDto.getParts());

        // Cars seeding
        CarsSeedRootDto carsSeedRootDto = xmlParser.fromFile(
                RESOURCES_FILES_PATH + CARS_FILE_NAME, CarsSeedRootDto.class
        );

        carService.seedCars(carsSeedRootDto.getCars());

        // Customers seeding
        CustomersSeedRootDto customersSeedRootDto = xmlParser.fromFile(
                RESOURCES_FILES_PATH + CUSTOMERS_FILE_NAME, CustomersSeedRootDto.class
        );

        customerService.seedCustomers(customersSeedRootDto.getCustomers());

        // Sales generating
        saleService.generateRandomSales(COUNT_OF_SALES);
    }
}
