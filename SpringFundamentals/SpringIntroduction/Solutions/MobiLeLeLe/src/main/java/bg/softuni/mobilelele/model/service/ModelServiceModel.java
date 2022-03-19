package bg.softuni.mobilelele.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModelServiceModel {
    private String name;
    private String category;
    private Integer startYear;
    private Integer endYear;
    private String imageUrl;
}
