package bg.softuni.mobilelele.model.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelBindingView {
    private Integer number;
    private String name;
    private String category;
    private Integer startYear;
    private Integer endYear;
    private String picture;
}
