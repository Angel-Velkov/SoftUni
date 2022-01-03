package com.example.nextleveltechnologies.controller;

import com.example.nextleveltechnologies.service.CompanyService;
import com.example.nextleveltechnologies.service.EmployeeService;
import com.example.nextleveltechnologies.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ImportController extends BaseController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public ImportController(CompanyService companyService, EmployeeService employeeService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/import/xml")
    public String importXml(Model model, HttpServletRequest request) {
        if (!super.isLogged(request)) {
            return "redirect:/";
        }

        model.addAttribute("areImported",
                new boolean[]{
                        this.companyService.areImported(),
                        this.employeeService.areImported(),
                        this.projectService.areImported()
                }
        );

        return "xml/import-xml";
    }

    @GetMapping("/import/companies")
    public String importCompanies(Model model, HttpServletRequest request) throws IOException {
        if (!super.isLogged(request)) {
            return "redirect:/";
        }

        model.addAttribute("companies", this.companyService.readCompaniesXmlFile());

        return "xml/import-companies";
    }

    @GetMapping("/import/employees")
    public String importEmployees(Model model, HttpServletRequest request) throws IOException {
        if (!super.isLogged(request)) {
            return "redirect:/";
        }

        model.addAttribute("employees", this.employeeService.readEmployeeXmlFile());

        return "xml/import-employees";
    }

    @GetMapping("/import/projects")
    public String importProjects(Model model, HttpServletRequest request) throws IOException {
        if (!super.isLogged(request)) {
            return "redirect:/";
        }

        model.addAttribute("projects", this.projectService.readProjectsXmlFile());

        return "xml/import-projects";
    }
}
