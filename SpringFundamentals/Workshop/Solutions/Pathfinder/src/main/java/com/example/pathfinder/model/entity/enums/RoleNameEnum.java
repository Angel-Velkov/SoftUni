package com.example.pathfinder.model.entity.enums;

public enum RoleNameEnum {
    USER("User"),
    MODERATOR("Moderator"),
    ADMIN("Admin");

    private final String name;

    RoleNameEnum(String name) {
        this.name = name;
    }

    public String getAsString() {
        return name;
    }
}
