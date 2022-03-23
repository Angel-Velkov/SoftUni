package com.example.pathfinder.model.entity.enums;

public enum LevelEnum {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private final String name;

    LevelEnum(String name) {
        this.name = name;
    }

    public String getAsString() {
        return name;
    }
}
