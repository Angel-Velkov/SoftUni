package bg.softuni.mobilelele.model.entity.enums;

public enum EngineEnum {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    String name;

    EngineEnum(String name) {
        this.name = name;
    }

    public String getAsString() {
        return name;
    }
}
