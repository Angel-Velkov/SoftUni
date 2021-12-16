package com.example.jsonprocessingexercise.service;

import com.example.jsonprocessingexercise.model.entity.User;

import java.io.IOException;

public interface UserService {

    void seedUsers() throws IOException;

    User getRandomUser();
}
