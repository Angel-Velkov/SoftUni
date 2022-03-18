package bg.softuni.mobilelele.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandWithModelNamesViewModel {
    private String name;
    private List<String> modelNames;
}
