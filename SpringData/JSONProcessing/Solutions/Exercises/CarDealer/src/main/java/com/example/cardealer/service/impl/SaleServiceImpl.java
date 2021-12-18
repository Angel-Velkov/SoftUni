package com.example.cardealer.service.impl;

import com.example.cardealer.constatnt.Percentage;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.model.entity.Customer;
import com.example.cardealer.model.entity.Sale;
import com.example.cardealer.rapository.SaleRepository;
import com.example.cardealer.service.CarService;
import com.example.cardealer.service.CustomerService;
import com.example.cardealer.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final CarService carService;
    private final CustomerService customerService;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(CarService carService, CustomerService customerService, SaleRepository saleRepository) {
        this.carService = carService;
        this.customerService = customerService;
        this.saleRepository = saleRepository;
    }

    @Override
    public void generateRandomSales(int countOfSales) {
        for (int i = 0; i < countOfSales; i++) {
            Car randomCar = carService.getRandomCar();
            Customer randomCustomer = customerService.getRandomCustomer();
            double discount = this.getRandomPercentage();

            Sale sale = new Sale(randomCar, randomCustomer, discount);

            this.saleRepository.save(sale);
        }
    }

    private double getRandomPercentage() {
        int countOfValues = Percentage.getSize();

        int randomIndex = ThreadLocalRandom.current().nextInt(countOfValues);
        Percentage[] percentages = Percentage.values();

        return percentages[randomIndex].getAsDouble();
    }
}
