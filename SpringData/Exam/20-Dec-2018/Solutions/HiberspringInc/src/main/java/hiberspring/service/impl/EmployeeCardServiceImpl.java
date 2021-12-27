package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.EmployeeCardImportDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private static final String EMPLOYEE_CARDS_JSON_FILES_PATH = PATH_TO_FILES + "employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, FileUtil fileUtil,
                                   Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {

        this.employeeCardRepository = employeeCardRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return fileUtil.readFile(EMPLOYEE_CARDS_JSON_FILES_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {
        EmployeeCardImportDto[] employeeCardImportDtos =
                gson.fromJson(employeeCardsFileContent, EmployeeCardImportDto[].class);

        StringBuilder importResult = new StringBuilder();

        for (EmployeeCardImportDto employeeCardImportDto : employeeCardImportDtos) {

            if (isValid(employeeCardImportDto)) {
                EmployeeCard card = modelMapper.map(employeeCardImportDto, EmployeeCard.class);
                employeeCardRepository.save(card);

                importResult.append(
                        String.format(SUCCESSFUL_IMPORT_MESSAGE, "Employee Card", card.getNumber())
                );
            } else {
                importResult.append(INCORRECT_DATA_MESSAGE);
            }

            importResult.append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public EmployeeCard findCardByNumber(String number) {
        return employeeCardRepository.findByNumber(number).orElse(null);
    }

    private boolean isValid(EmployeeCardImportDto employeeCardImportDto) {
        return validationUtil.isValid(employeeCardImportDto)
                && !employeeCardRepository.existsByNumber(employeeCardImportDto.getNumber());
    }
}
