package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.view.BrandWithModelNamesViewModel;

import java.util.List;

public interface BrandService {
    List<BrandWithModelNamesViewModel> getAllBrandsWithModels();
}
