package com.example.xmlcardealer.service.impl;

import com.example.xmlcardealer.model.dto.CustomerSeedDto;
import com.example.xmlcardealer.model.entity.Customer;
import com.example.xmlcardealer.repository.CustomerRepository;
import com.example.xmlcardealer.service.CustomerService;
import com.example.xmlcardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ValidationUtil validationUtil, ModelMapper mapper) {

        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public void seedCustomers(Collection<CustomerSeedDto> customers) {
        customers
                .stream()
                .filter(validationUtil::isValid)
                .map(customerSeedDto -> mapper.map(customerSeedDto, Customer.class))
                .forEach(customerRepository::save);
    }

    @Override
    public Customer getRandomCustomer() {
        long count = this.customerRepository.count();

        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);

        return this.customerRepository.findById(randomId).orElse(null);
    }
}
