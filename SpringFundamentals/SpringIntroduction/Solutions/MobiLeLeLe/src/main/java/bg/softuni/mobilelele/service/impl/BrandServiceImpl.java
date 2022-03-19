package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.service.BrandServiceModel;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BrandServiceModel> findAllBrandsWithTheirModels() {
        return this.brandRepository.findAll()
                .stream()
                .map(brand -> this.mapper.map(brand, BrandServiceModel.class))
                .collect(Collectors.toList());
    }
}
