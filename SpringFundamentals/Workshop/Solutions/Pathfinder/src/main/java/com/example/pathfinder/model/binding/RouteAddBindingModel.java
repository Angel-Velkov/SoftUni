package com.example.pathfinder.model.binding;

import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RouteAddBindingModel {
    @NotBlank
    @Size(min = 3, max = 20, message = "Route name must be between 3 and 20 characters")
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private MultipartFile gpxCoordinates;

    @NotNull
    private LevelEnum level;

    @NotBlank
    private String videoUrl;

    @NotNull
    @Size(min = 1)
    private Set<CategoryNameEnum> categories;
}
