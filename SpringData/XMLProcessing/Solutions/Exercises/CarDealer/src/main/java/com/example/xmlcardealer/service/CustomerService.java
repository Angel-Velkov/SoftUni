package com.example.xmlcardealer.service;

import com.example.xmlcardealer.model.dto.CustomerSeedDto;
import com.example.xmlcardealer.model.entity.Customer;

import java.util.Collection;

public interface CustomerService {

    void seedCustomers(Collection<CustomerSeedDto> customers);

    Customer getRandomCustomer();
}
