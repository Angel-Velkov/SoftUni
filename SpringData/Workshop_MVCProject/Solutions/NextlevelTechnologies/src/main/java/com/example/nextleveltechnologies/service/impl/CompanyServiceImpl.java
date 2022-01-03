package com.example.nextleveltechnologies.service.impl;

import com.example.nextleveltechnologies.repository.CompanyRepository;
import com.example.nextleveltechnologies.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.existsAllBy();
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return Files.readString(Path.of(COMPANIES_XML_FILES_PATH));
    }
}
