package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.RoleRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
}
