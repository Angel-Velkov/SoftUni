package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.view.BrandWithModelNamesViewModel;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandWithModelNamesViewModel> findAllBrandsWithTheirModels() {
        return this.brandRepository.findAll()
                .stream()
                .map(brand ->
                        new BrandWithModelNamesViewModel(brand.getName(),
                                brand
                                        .getModels()
                                        .stream()
                                        .map(ModelEntity::getName)
                                        .collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
    }
}
