package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelEntity findModelByName(String name) {
        return this.modelRepository.findByName(name).orElse(null);
    }
}
