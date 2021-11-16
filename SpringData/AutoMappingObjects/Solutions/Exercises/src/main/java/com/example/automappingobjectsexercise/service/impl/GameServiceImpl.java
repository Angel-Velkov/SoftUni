package com.example.automappingobjectsexercise.service.impl;

import com.example.automappingobjectsexercise.model.dto.GameDto;
import com.example.automappingobjectsexercise.model.entity.Game;
import com.example.automappingobjectsexercise.repository.GameRepository;
import com.example.automappingobjectsexercise.service.GameService;
import com.example.automappingobjectsexercise.service.UserService;
import com.example.automappingobjectsexercise.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    private final UserService userService;
    private final GameRepository gameRepository;
    private final ModelMapper mapper;
    private final Validation validation;

    @Autowired
    public GameServiceImpl(UserService userService, GameRepository gameRepository,
                           ModelMapper mapper, Validation validation) {

        this.userService = userService;
        this.gameRepository = gameRepository;
        this.mapper = mapper;
        this.validation = validation;
    }

    @Override
    public String addGame(GameDto gameAddDto) {
        // this.ensureItIsAnAdmin();

        this.validation.validateEntity(gameAddDto);

        Game game = this.mapper.map(gameAddDto, Game.class);

        this.gameRepository.save(game);

        return game.getTitle();
    }

    @Transactional
    @Override
    public String editGame(long id, Map<String, String> fieldNamesAndValues) throws NoSuchFieldException, IllegalAccessException {
        Game game = this.findGame(id);

        // Validation
        this.validateGameProperties(fieldNamesAndValues, game);

        for (Map.Entry<String, String> nameAndValue : fieldNamesAndValues.entrySet()) {
            this.setTheObjectField(game, nameAndValue.getKey(), nameAndValue.getValue());
        }

        this.gameRepository.save(game);

        return game.getTitle();
    }

    @Transactional
    @Override
    public String deleteGame(long id) {
        Game game = this.findGame(id);

        this.gameRepository.deleteById(id);

        return game.getTitle();
    }

    private Game findGame(long id) {
        return this.gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no game with such an id"));
    }

    // It is easier to validate it through the DTO
    private void validateGameProperties(Map<String, String> fieldNamesAndValues, Game game) throws NoSuchFieldException, IllegalAccessException {
        GameDto gameDto = mapper.map(game, GameDto.class);

        for (Map.Entry<String, String> nameAndValue : fieldNamesAndValues.entrySet()) {
            this.setTheObjectField(gameDto, nameAndValue.getKey(), nameAndValue.getValue());
        }

        this.validation.validateEntity(gameDto);
    }

    private <T> void setTheObjectField(T object, String fieldName, String fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        Class<?> type = field.getType();
        field.set(object, convert(type, fieldValue));
    }

    private Object convert(Class<?> targetType, String value) {
        if (targetType == LocalDate.class) {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else if (targetType == BigDecimal.class) {
            return new BigDecimal(value);
        }

        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(value);
        return editor.getValue();
    }

    private void ensureItIsAnAdmin() {
        if (!this.userService.isAdmin()) {
            throw new IllegalStateException(
                    "Only a user with administrative rights can add/edit/delete games to the catalog"
            );
        }
    }
}