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
    public String registerUser(UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            throw new IllegalArgumentException("The passwords you entered do not match.");
        }

        this.validation.validateEntity(userRegistrationDto);

        User user = this.mapper.map(userRegistrationDto, User.class);

        this.userRepository.save(user);

        return user.getFullName();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {

        this.validation.validateEntity(userLoginDto);

        User user = this.userRepository.findByEmailAndPassword(
                userLoginDto.getEmail(), userLoginDto.getPassword()
        ).orElseThrow(() -> new IllegalArgumentException("Incorrect username / password"));

        this.loggedInUser = user;

        return user.getFullName();
    }

    @Override
    public String logout() {
        if (this.loggedInUser != null) {
            String fullName = this.loggedInUser.getFullName();

            this.loggedInUser = null;

            return fullName;
        } else {
            throw new IllegalStateException("Cannot log out. No user was logged in.");
        }
    }

    @Override
    public boolean isAdmin() {
        return this.loggedInUser != null && this.loggedInUser.isAdministrator();
    }
}
