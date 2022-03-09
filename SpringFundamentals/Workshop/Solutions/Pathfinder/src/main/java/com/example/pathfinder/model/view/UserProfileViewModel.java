package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserProfileViewModel {
    private Long id;
    private String fullName;
    private String username;
    private Integer age;
    private LevelEnum level;
}
