package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.TeamImportDto;
import softuni.exam.domain.dto.TeamsImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.PictureService;
import softuni.exam.service.TeamService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static softuni.exam.common.Constants.*;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String TEAMS_XML_FILES_PATH = PATH_TO_FILES_XML + "teams.xml";

    private final TeamRepository teamRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, FileUtil fileUtil,
                           XmlParser xmlParser, ValidatorUtil validatorUtil,
                           ModelMapper modelMapper, PictureService pictureService) {

        this.teamRepository = teamRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @Override
    public String importTeams() throws JAXBException {
        TeamsImportRootDto rootDto =
                xmlParser.parseXml(TeamsImportRootDto.class, TEAMS_XML_FILES_PATH);

        StringBuilder importResult = new StringBuilder();

        for (TeamImportDto teamDto : rootDto.getTeams()) {
            boolean isValid = validatorUtil.isValid(teamDto);

            if (isValid) {
                Team team = modelMapper.map(teamDto, Team.class);

                Picture picture = pictureService.findPictureByUrl(teamDto.getPicture().getUrl());

                if (picture != null) {
                    team.setPicture(picture);
                    teamRepository.save(team);

                    importResult.append(
                            String.format(SUCCESSFUL_IMPORT_MESSAGE, "team", team.getName())
                    );
                } else {
                    isValid = false;
                }
            }

            if (!isValid) {
                importResult.append(
                        String.format(INCORRECT_DATA_MESSAGE, "team")
                );
            }

            importResult.append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return fileUtil.readFile(TEAMS_XML_FILES_PATH);
    }

    @Override
    public Team findTeamByName(String name) {
        return teamRepository.findByName(name).orElse(null);
    }
}
