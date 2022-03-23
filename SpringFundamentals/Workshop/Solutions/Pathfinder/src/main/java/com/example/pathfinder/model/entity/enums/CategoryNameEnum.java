package com.example.pathfinder.model.entity.enums;

public enum CategoryNameEnum {
    PEDESTRIAN("Pedestrian"),
    BICYCLE("Bicycle"),
    MOTORCYCLE("Motorcycle"),
    CAR("Car");

    private final String name;

    CategoryNameEnum(String name) {
        this.name = name;
    }

    public String getAsString() {
        return name;
    }
}
