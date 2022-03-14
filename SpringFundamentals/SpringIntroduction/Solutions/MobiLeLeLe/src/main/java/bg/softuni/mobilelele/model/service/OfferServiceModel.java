package bg.softuni.mobilelele.model.service;

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

    private String engineType;

    private String imageUrl;

    private Integer mileage;

    private Integer price;

    private String transmissionType;

    private Integer year;

    private String modelName;

    private String modelBrandName;

    private LocalDateTime created;

    private LocalDateTime modified;
}
