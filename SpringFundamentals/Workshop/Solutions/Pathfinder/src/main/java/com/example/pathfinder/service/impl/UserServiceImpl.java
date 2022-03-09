package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.RoleRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.secutity.CurrentUser;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           CurrentUser currentUser, ModelMapper mapper) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userModel) {
        userModel.setLevel(LevelEnum.BEGINNER);
        userModel.getRoles().add(roleRepository.findByRole(RoleNameEnum.USER));

        UserEntity user = this.mapper.map(userModel, UserEntity.class);
        this.userRepository.save(user);

        return userModel;
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password)
                .map(user -> mapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        this.currentUser.setId(id);
        this.currentUser.setUsername(username);
    }

    @Override
    public void logout() {
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
    }

    @Override
    public UserServiceModel findById(Long id) {
        return this.userRepository.findById(id)
                .map(user -> this.mapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public boolean containsUser(String username) {
        return this.userRepository.existsByUsername(username);
    }
}
