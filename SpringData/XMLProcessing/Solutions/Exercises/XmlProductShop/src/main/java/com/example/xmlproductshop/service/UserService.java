package com.example.xmlproductshop.service;

import com.example.xmlproductshop.model.dto.UserSeedDto;
import com.example.xmlproductshop.model.entity.User;

import java.util.List;

public interface UserService {

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();
}
