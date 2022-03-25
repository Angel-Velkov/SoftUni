package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.EmployeeViewModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    boolean isUsernameFree(String username);

    boolean containsUser(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    UserEntity getCurrentLoggedInUser();

    List<EmployeeViewModel> findAllEmployees();
}
