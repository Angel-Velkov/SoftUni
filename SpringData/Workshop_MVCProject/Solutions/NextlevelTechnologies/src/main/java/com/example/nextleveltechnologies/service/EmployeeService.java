package com.example.nextleveltechnologies.service;

import java.io.IOException;

import static com.example.nextleveltechnologies.common.Constants.PATH_FILES_XMLS;

public interface EmployeeService {

    String EMPLOYEES_XML_FILES_PATH = PATH_FILES_XMLS + "employees.xml";

    boolean areImported();

    String readEmployeeXmlFile() throws IOException;
}
