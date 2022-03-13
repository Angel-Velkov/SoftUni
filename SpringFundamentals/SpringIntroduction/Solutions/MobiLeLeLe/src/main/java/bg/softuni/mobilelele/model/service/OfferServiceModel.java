package bg.softuni.mobilelele.model.service;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OfferServiceModel {

    private Long sellerId;

    private String description;

    private EngineEnum engine;

    private String imageUrl;

    private Integer mileage;

    private Integer price;

    private TransmissionEnum transmission;

    private Integer year;

    private String model;
}
