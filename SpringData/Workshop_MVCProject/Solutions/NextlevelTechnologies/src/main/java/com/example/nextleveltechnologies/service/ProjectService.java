package com.example.nextleveltechnologies.service;

import java.io.IOException;

import static com.example.nextleveltechnologies.common.Constants.PATH_FILES_XMLS;

public interface ProjectService {

    String PROJECTS_XML_FILES_PATH = PATH_FILES_XMLS + "projects.xml";

    boolean areImported();

    String readProjectsXmlFile() throws IOException;
}
