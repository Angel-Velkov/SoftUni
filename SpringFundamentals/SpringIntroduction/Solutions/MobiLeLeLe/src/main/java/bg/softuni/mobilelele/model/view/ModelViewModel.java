package bg.softuni.mobilelele.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModelViewModel {
    private String name;
    private String category;
    private Integer startYear;
    private Integer endYear;
    private String imageUrl;
}
