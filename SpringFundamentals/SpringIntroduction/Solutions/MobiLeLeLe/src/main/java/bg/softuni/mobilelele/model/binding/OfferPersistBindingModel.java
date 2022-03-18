package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class OfferPersistBindingModel {

    @NotBlank
    private String modelName;

    @NotNull
    @PositiveOrZero
    private Integer price;

    @NotNull
    private EngineEnum engine;

    @NotNull
    private TransmissionEnum transmission;

    @NotNull
    @Min(1900)
    @Max(2099)
    private Integer year;

    @NotNull
    @PositiveOrZero
    @Max(900_000)
    private Integer mileage;

    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;
}
