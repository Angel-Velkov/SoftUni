package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.view.BrandWithModelsViewModel;

import java.util.List;

public interface BrandService {
    List<BrandWithModelsViewModel> getAllBrandsWithModels();
}
