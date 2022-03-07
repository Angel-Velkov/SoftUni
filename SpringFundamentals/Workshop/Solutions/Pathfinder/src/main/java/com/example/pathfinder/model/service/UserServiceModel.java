package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.RoleEntity;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserServiceModel {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private Integer age;

    private LevelEnum level;

    private Set<RoleEntity> roles;

}
