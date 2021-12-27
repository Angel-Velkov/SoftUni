package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.TownImportDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_JSON_FILES_PATH = PATH_TO_FILES + "towns.json";

    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil,
                           Gson gson, ValidationUtil validationUtil,
                           ModelMapper modelMapper) {

        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return fileUtil.readFile(TOWNS_JSON_FILES_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        TownImportDto[] townImportDtos = gson.fromJson(townsFileContent, TownImportDto[].class);

        StringBuilder importResult = new StringBuilder();

        for (TownImportDto townImportDto : townImportDtos) {

            if (validationUtil.isValid(townImportDto)) {
                Town town = modelMapper.map(townImportDto, Town.class);
                townRepository.save(town);

                importResult.append(
                        String.format(SUCCESSFUL_IMPORT_MESSAGE, "Town", town.getName())
                );
            } else {
                importResult.append(INCORRECT_DATA_MESSAGE);
            }

            importResult.append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public Town findTownByName(String name) {
        return townRepository.findByName(name).orElse(null);
    }
}
