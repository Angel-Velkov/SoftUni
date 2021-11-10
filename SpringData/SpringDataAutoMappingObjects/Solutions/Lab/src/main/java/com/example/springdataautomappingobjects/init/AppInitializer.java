package com.example.springdataautomappingobjects.init;

import com.example.springdataautomappingobjects.dto.EmployeeDto;
import com.example.springdataautomappingobjects.entities.Address;
import com.example.springdataautomappingobjects.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        //1. Simple Mapping
        Address address = new Address("Bulgaria", "Sofia", "some random street");
        Employee employee = new Employee("Angel", "Velkov", 420.69, LocalDate.MIN, address);

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        System.out.println(employeeDto);
    }
}
