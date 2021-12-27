package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.service.BranchService;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class BranchServiceImpl implements BranchService {

    private static final String BRANCHES_JSON_FILES_PATH = PATH_TO_FILES + "branches.json";

    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownService townService;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil,
                             Gson gson, ValidationUtil validationUtil,
                             TownService townService, ModelMapper modelMapper) {

        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townService = townService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean branchesAreImported() {
        return branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return fileUtil.readFile(BRANCHES_JSON_FILES_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) {
        BranchImportDto[] branchImportDtos = gson.fromJson(branchesFileContent, BranchImportDto[].class);

        StringBuilder importResult = new StringBuilder();

        for (BranchImportDto branchImportDto : branchImportDtos) {
            boolean isValid = false;

            if (validationUtil.isValid(branchImportDto)) {
                isValid = true;

                Town town = townService.findTownByName(branchImportDto.getTown());

                if (town != null) {
                    Branch branch = modelMapper.map(branchImportDto, Branch.class);
                    branch.setTown(town);

                    branchRepository.save(branch);

                    importResult.append(
                            String.format(SUCCESSFUL_IMPORT_MESSAGE, "Branch", branch.getName())
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
    public Branch findBranchByName(String name) {
        return branchRepository.findByName(name).orElse(null);
    }
}
