package bg.softuni.mobilelele.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BrandServiceModel {
    private String name;
    private List<ModelServiceModel> models;
}
