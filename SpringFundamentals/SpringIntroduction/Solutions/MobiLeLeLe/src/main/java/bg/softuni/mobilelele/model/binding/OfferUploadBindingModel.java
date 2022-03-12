package bg.softuni.mobilelele.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class OfferUploadBindingModel {
    private String model;
    private BigDecimal price;
    private String engine;
    private String transmission;
    private Integer year;
    private Integer mileage;
    private String description;
    private String imageUrl;
}
