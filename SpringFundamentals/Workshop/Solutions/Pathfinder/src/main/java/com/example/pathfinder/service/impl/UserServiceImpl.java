package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.RoleRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           ModelMapper mapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userModel) {
        userModel.setLevel(LevelEnum.BEGINNER);
        userModel.getRoles().add(roleRepository.findByRole(RoleNameEnum.USER));

        UserEntity userEntity = this.mapper.map(userModel, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        this.userRepository.save(userEntity);

        return userModel;
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
