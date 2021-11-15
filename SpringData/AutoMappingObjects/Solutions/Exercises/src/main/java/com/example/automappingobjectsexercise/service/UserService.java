package com.example.automappingobjectsexercise.service;

import com.example.automappingobjectsexercise.model.dto.UserLoginDto;
import com.example.automappingobjectsexercise.model.dto.UserRegistrationDto;
import com.example.automappingobjectsexercise.model.entity.User;

public interface UserService {
    String registerUser(UserRegistrationDto user);

    String loginUser(UserLoginDto userLoginDto);

    String logout();
}
