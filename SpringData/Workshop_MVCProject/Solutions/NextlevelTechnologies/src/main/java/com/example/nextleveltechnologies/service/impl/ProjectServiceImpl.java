package com.example.nextleveltechnologies.service.impl;

import com.example.nextleveltechnologies.repository.ProjectRepository;
import com.example.nextleveltechnologies.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public boolean areImported() {
        return this.projectRepository.existsAllBy();
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return Files.readString(Path.of(PROJECTS_XML_FILES_PATH));
    }
}
