package bg.softuni.mobilelele.model.entity.enums;

public enum TransmissionEnum {
    MANUAL("Manual"),
    AUTOMATIC("Automatic");

    String name;

    TransmissionEnum(String name) {
        this.name = name;
    }

    public String getAsString() {
        return name;
    }
}
