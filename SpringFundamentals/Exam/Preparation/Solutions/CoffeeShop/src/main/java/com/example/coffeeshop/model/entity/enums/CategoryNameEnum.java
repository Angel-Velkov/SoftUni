package com.example.coffeeshop.model.entity.enums;

public enum CategoryNameEnum {
    COFFEE("Coffee", 2),
    CAKE("Cake", 10),
    DRINK("Drink", 1),
    OTHER("Other", 5);

    private final String name;
    private final int neededTime;

    CategoryNameEnum(String name, int neededTime) {
        this.name = name;
        this.neededTime = neededTime;
    }

    public String getName() {
        return this.name;
    }

    public int getNeededTime() {
        return this.neededTime;
    }
}
