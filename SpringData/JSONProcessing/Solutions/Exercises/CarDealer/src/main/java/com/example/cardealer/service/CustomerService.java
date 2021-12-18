package com.example.cardealer.service;

import com.example.cardealer.model.dto.CustomerWithSalesDto;
import com.example.cardealer.model.entity.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    void seedCustomers() throws IOException;

    Customer getRandomCustomer();

    List<CustomerWithSalesDto> getOrderedCustomers();
}
