package com.example.jsonprocessingexercise.service.impl;

import com.example.jsonprocessingexercise.model.dto.UserSeedDto;
import com.example.jsonprocessingexercise.model.entity.User;
import com.example.jsonprocessingexercise.repository.UserRepository;
import com.example.jsonprocessingexercise.service.UserService;
import com.example.jsonprocessingexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.jsonprocessingexercise.constant.GlobalConstant.RESOURCES_FILE_PATH;

@Service
public class UserServiceImpl implements UserService {

    public static final String USERS_FILE_NAME = "users.json";

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper,
                           ValidationUtil validationUtil, Gson gson) {

        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        String fileContents = Files.readString(Path.of(RESOURCES_FILE_PATH + USERS_FILE_NAME));

        UserSeedDto[] userSeedDtos = gson.fromJson(fileContents, UserSeedDto[].class);

        Arrays.stream(userSeedDtos)
                .filter(validationUtil::isValid)
                .map(userSeedDto -> mapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long usersTotalCount = this.userRepository.count();

        long randomId = ThreadLocalRandom
                .current()
                .nextLong(1, usersTotalCount + 1);

        return this.userRepository
                .findById(randomId)
                .orElse(null);
    }
}
