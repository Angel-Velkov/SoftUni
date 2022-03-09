package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private UserEntity author;

    private String videoUrl;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<PictureEntity> pictures = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CategoryEntity> categories = new HashSet<>();
}
