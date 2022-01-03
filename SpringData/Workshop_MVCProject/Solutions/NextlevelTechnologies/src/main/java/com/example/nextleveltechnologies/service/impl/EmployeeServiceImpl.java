package com.example.nextleveltechnologies.service.impl;

import com.example.nextleveltechnologies.repository.EmployeeRepository;
import com.example.nextleveltechnologies.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.existsAllBy();
    }

    @Override
    public String readEmployeeXmlFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEES_XML_FILES_PATH));
    }
}
