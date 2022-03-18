package com.example.pathfinder.service;

import com.example.pathfinder.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserServiceModel findById(Long id);

    boolean containsUser(String username);
}