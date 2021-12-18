package com.example.cardealer.constatnt;

public enum Percentage {
    ZERO(0),
    FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50);

    private final double asDouble;

    Percentage(int asDouble) {
        this.asDouble = asDouble;
    }

    public static int getSize() {
        return values().length;
    }

    public double getAsDouble() {
        return this.asDouble;
    }
}
