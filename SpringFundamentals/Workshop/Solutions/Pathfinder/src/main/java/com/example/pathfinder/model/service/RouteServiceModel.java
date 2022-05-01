package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RouteServiceModel {
    private String name;
    private String description;
    private String gpxCoordinates;
    private LevelEnum level;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;
}