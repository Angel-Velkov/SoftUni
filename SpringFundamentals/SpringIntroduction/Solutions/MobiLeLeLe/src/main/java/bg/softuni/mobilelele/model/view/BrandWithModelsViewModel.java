package bg.softuni.mobilelele.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandWithModelsViewModel {
    String name;
    Set<String> models;
}
