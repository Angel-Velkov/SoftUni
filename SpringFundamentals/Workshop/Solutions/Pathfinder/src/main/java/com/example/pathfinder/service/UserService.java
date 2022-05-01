package com.example.pathfinder.service;

import com.example.pathfinder.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userModel);

    UserServiceModel findById(Long id);

    boolean containsUser(String username);
}
