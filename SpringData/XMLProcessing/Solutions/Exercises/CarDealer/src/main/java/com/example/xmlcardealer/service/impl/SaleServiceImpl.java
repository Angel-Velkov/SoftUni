package com.example.xmlcardealer.service.impl;

import com.example.xmlcardealer.constants.Discounts;
import com.example.xmlcardealer.model.entity.Car;
import com.example.xmlcardealer.model.entity.Customer;
import com.example.xmlcardealer.model.entity.Sale;
import com.example.xmlcardealer.repository.SaleRepository;
import com.example.xmlcardealer.service.CarService;
import com.example.xmlcardealer.service.CustomerService;
import com.example.xmlcardealer.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService,
                           CustomerService customerService) {

        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void generateRandomSales(int countOfSales) {
        for (int i = 0; i < countOfSales; i++) {
            Car randomCar = carService.getRandomCar();
            Customer randomCustomer = customerService.getRandomCustomer();
            BigDecimal discount = this.getRandomPercentage();

            Sale sale = new Sale(randomCar, randomCustomer, discount);

            this.saleRepository.save(sale);
        }
    }

    private BigDecimal getRandomPercentage() {
        int countOfValues = Discounts.getSize();

        int randomIndex = ThreadLocalRandom.current().nextInt(countOfValues);
        Discounts[] percentages = Discounts.values();

        return percentages[randomIndex].getDecimal();
    }
}
