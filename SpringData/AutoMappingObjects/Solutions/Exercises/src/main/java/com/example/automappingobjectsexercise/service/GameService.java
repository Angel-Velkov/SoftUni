package com.example.automappingobjectsexercise.service;

import com.example.automappingobjectsexercise.model.dto.GameDto;

import java.util.Map;

public interface GameService {

    String addGame(GameDto gameAddDto);

    String editGame(long id, Map<String, String> fieldNamesAndValues) throws NoSuchFieldException, IllegalAccessException;

    String deleteGame(long id);
}
