package bg.softuni.mobilelele.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OfferSummaryViewModel {
    private Long id;
    private String imageUrl;
    private Integer year;
    private String brandName;
    private String modelName;
    private Integer mileage;
    private Integer price;
    private String engineType;
    private String transmissionType;
}
