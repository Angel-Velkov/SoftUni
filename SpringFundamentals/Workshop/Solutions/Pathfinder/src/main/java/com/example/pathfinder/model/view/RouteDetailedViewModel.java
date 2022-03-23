package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RouteDetailedViewModel {
    private String name;
    private String authorName;
    private LevelEnum level;
    private String videoUrl;
    private String description;
    private Set<String> pictureUrls;
}
