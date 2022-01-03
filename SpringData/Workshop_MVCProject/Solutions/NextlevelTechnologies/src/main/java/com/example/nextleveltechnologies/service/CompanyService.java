package com.example.nextleveltechnologies.service;

import java.io.IOException;

import static com.example.nextleveltechnologies.common.Constants.PATH_FILES_XMLS;

public interface CompanyService {

    String COMPANIES_XML_FILES_PATH = PATH_FILES_XMLS + "companies.xml";

    boolean areImported();

    String readCompaniesXmlFile() throws IOException;
}
