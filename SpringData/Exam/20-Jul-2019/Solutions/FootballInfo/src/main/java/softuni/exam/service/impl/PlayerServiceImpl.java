package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PlayerImportDto;
import softuni.exam.domain.dto.PlayerInfoDto;
import softuni.exam.domain.dto.PlayerViewDto;
import softuni.exam.domain.dto.TeamViewDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.service.PictureService;
import softuni.exam.service.PlayerService;
import softuni.exam.service.TeamService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static softuni.exam.common.Constants.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYERS_JSON_FILES_PATH = PATH_TO_FILES_JSON + "players.json";

    private static final String TEAM_NAME = "City";
    private static final BigDecimal SALARY = BigDecimal.valueOf(100_000);

    private final PlayerRepository playerRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;
    private final TeamService teamService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, FileUtil fileUtil,
                             Gson gson, ValidatorUtil validatorUtil,
                             ModelMapper modelMapper, PictureService pictureService,
                             TeamService teamService) {

        this.playerRepository = playerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.teamService = teamService;
    }

    @Override
    public String importPlayers() throws IOException {
        PlayerImportDto[] playerImportDtos =
                gson.fromJson(fileUtil.readFile(PLAYERS_JSON_FILES_PATH), PlayerImportDto[].class);

        StringBuilder importResult = new StringBuilder();

        for (PlayerImportDto playerDto : playerImportDtos) {
            boolean isValid = validatorUtil.isValid(playerDto);

            if (isValid) {
                Picture picture = pictureService.findPictureByUrl(playerDto.getPicture().getUrl());
                Team team = teamService.findTeamByName(playerDto.getTeam().getName());

                if (picture != null && team != null) {
                    Player player = modelMapper.map(playerDto, Player.class);
                    player.setPicture(picture);
                    player.setTeam(team);

                    playerRepository.save(player);

                    importResult.append(
                            String.format(SUCCESSFUL_IMPORT_MESSAGE,
                                    "player", player.getFirstName() + " " + player.getLastName())
                    );
                } else {
                    isValid = false;
                }
            }

            if (!isValid) {
                importResult.append(
                        String.format(INCORRECT_DATA_MESSAGE, "player")
                );
            }

            importResult.append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return fileUtil.readFile(PLAYERS_JSON_FILES_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        List<PlayerInfoDto> playerDtos =
                playerRepository.findAllWithSalaryBiggerThanOrderedBySalaryDesc(SALARY);

        return this.serializeAsView(playerDtos);
    }

    private String serializeAsView(Iterable<PlayerInfoDto> playerInfoDtos) {
        StringBuilder result = new StringBuilder();

        for (PlayerInfoDto playerDto : playerInfoDtos) {
            result.append(
                    String.format("Player name: %s %s%n" +
                                    "    Number: %d%n" +
                                    "    Salary: %s%n" +
                                    "    Team: %s%n",
                            playerDto.getFirstName(),
                            playerDto.getLastName(),
                            playerDto.getNumber(),
                            playerDto.getSalary(),
                            playerDto.getTeamName())
            );
        }

        return result.toString().trim();
    }

    @Override
    public String exportPlayersInATeam() {
        List<PlayerViewDto> playerDtos =
                playerRepository.findAllByTeam_Name(TEAM_NAME);

        TeamViewDto teamViewDto = new TeamViewDto();
        teamViewDto.setName(TEAM_NAME);
        teamViewDto.setPlayers(playerDtos);

        return this.serializeAsView(teamViewDto);
    }

    private String serializeAsView(TeamViewDto teamViewDto) {
        StringBuilder result = new StringBuilder();

        result.append("Team: ").append(teamViewDto.getName())
                .append(System.lineSeparator());

        for (PlayerViewDto playerDto : teamViewDto.getPlayers()) {
            result.append(
                    String.format("   Player name: %s %s - %s%n" +
                                    "   Number: %d%n",
                            playerDto.getFirstName(),
                            playerDto.getLastName(),
                            playerDto.getPosition(),
                            playerDto.getNumber())
            );
        }

        return result.toString().trim();
    }
}
