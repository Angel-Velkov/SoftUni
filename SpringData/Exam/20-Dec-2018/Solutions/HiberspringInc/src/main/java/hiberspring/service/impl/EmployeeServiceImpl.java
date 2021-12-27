package hiberspring.service.impl;

import hiberspring.domain.dtos.EmployeeImportDto;
import hiberspring.domain.dtos.EmployeesImportRootDto;
import hiberspring.domain.dtos.ProductiveEmployeeDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.BranchService;
import hiberspring.service.EmployeeCardService;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEES_XML_FILES_PATH = PATH_TO_FILES + "employees.xml";

    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final EmployeeCardService employeeCardService;
    private final BranchService branchService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               FileUtil fileUtil, XmlParser xmlParser,
                               ValidationUtil validationUtil, EmployeeCardService employeeCardService,
                               BranchService branchService, ModelMapper modelMapper) {

        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.employeeCardService = employeeCardService;
        this.branchService = branchService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return fileUtil.readFile(EMPLOYEES_XML_FILES_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException {
        EmployeesImportRootDto rootDto =
                xmlParser.parseXml(EmployeesImportRootDto.class, EMPLOYEES_XML_FILES_PATH);

        StringBuilder importResult = new StringBuilder();

        for (EmployeeImportDto employeeDto : rootDto.getEmployees()) {
            boolean isValid = false;

            if (validationUtil.isValid(employeeDto)) {
                isValid = true;

                EmployeeCard card = employeeCardService.findCardByNumber(employeeDto.getCardNumber());
                Branch branch = branchService.findBranchByName(employeeDto.getBranchName());

                if (card != null && branch != null
                        && !employeeRepository.existsByCard_Number(card.getNumber())) {

                    Employee employee = modelMapper.map(employeeDto, Employee.class);
                    employee.setCard(card);
                    employee.setBranch(branch);

                    employeeRepository.save(employee);

                    importResult.append(
                            String.format(SUCCESSFUL_IMPORT_MESSAGE,
                                    "Employee", employee.getFirstName() + " " + employee.getLastName())
                    );
                } else {
                    isValid = false;
                }
            }

            if (!isValid) {
                importResult.append(INCORRECT_DATA_MESSAGE);
            }

            importResult.append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        List<ProductiveEmployeeDto> productiveEmployees =
                employeeRepository.findAllEmployeesInBranchWithAtLeastOneProduct();

        return productiveEmployees
                .stream()
                .map(serializeProductiveEmployee)
                .collect(Collectors.joining());
    }

    // Just wanted to write a function
    // (I don't know if the dashes should be in a separate constant)
    private static final Function<ProductiveEmployeeDto, String> serializeProductiveEmployee =
            employee -> String.format(
                    "Name: %s%n"
                            + "Position: %s%n"
                            + "Card Number: %s%n"
                            + "%s%n",
                    employee.getName(),
                    employee.getPosition(),
                    employee.getCardNumber(),
                    "-".repeat(25)
            );
}
