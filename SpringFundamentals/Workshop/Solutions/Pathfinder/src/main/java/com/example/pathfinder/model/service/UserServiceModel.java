package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.RoleEntity;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserServiceModel {

    private Long id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private Integer age;

    private LevelEnum level;

    private Set<RoleEntity> roles = new HashSet<>();
}
