package com.example.automappingobjectsexercise.service.impl;

import com.example.automappingobjectsexercise.model.dto.UserLoginDto;
import com.example.automappingobjectsexercise.model.dto.UserRegistrationDto;
import com.example.automappingobjectsexercise.model.entity.User;
import com.example.automappingobjectsexercise.repository.UserRepository;
import com.example.automappingobjectsexercise.service.UserService;
import com.example.automappingobjectsexercise.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final Validation validation;

    private User loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, Validation validation) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validation = validation;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            throw new IllegalArgumentException("The passwords you entered do not match.");
        }

        this.validateEntity(userRegistrationDto);

        User user = this.mapper.map(userRegistrationDto, User.class);

        this.userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {

        this.validateEntity(userLoginDto);

        User user = this.userRepository.findByEmailAndPassword(
                userLoginDto.getEmail(), userLoginDto.getPassword()
        ).orElseThrow(() -> new IllegalArgumentException("Incorrect username / password"));

        this.loggedInUser = user;
    }

    private <E> void validateEntity(E entity) {
        Set<ConstraintViolation<E>> violations = this.validation.violation(entity);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator()))
            );
        }
    }
}
