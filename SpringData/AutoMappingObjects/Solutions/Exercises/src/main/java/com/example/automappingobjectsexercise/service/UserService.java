package com.example.automappingobjectsexercise.service;

import com.example.automappingobjectsexercise.model.dto.UserLoginDto;
import com.example.automappingobjectsexercise.model.dto.UserRegistrationDto;

public interface UserService {
    void registerUser(UserRegistrationDto user);

    void loginUser(UserLoginDto userLoginDto);
}
