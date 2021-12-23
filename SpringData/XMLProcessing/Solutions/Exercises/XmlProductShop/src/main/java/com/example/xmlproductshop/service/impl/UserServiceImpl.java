package com.example.xmlproductshop.service.impl;

import com.example.xmlproductshop.model.dto.UserSeedDto;
import com.example.xmlproductshop.model.entity.User;
import com.example.xmlproductshop.repository.UserRepository;
import com.example.xmlproductshop.service.UserService;
import com.example.xmlproductshop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository,
                           ValidationUtil validationUtil, ModelMapper mapper) {

        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users
                .stream()
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
