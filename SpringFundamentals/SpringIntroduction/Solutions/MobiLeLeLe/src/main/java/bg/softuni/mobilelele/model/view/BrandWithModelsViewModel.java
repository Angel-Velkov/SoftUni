package bg.softuni.mobilelele.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BrandWithModelsViewModel {
    private String name;
    private List<ModelViewModel> models;
}
