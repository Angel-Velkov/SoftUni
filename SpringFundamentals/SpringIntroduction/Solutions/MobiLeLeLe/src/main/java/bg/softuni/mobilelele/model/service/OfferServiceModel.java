package bg.softuni.mobilelele.model.service;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OfferServiceModel {

    private Long id;

    private UserServiceModel seller;

    private String description;

    private String imageUrl;

    private Integer mileage;

    private Integer price;

    private EngineEnum engine;

    private TransmissionEnum transmission;

    private Integer year;

    private String modelName;

    private String modelBrandName;

    private LocalDateTime created;

    private LocalDateTime modified;
}
