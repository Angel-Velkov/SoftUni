package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.OfferServiceModel;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.ModelService;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelService modelService;
    private final ModelMapper mapper;
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(CurrentUser currentUser, UserService userService,
                            ModelService modelService, ModelMapper mapper,
                            OfferRepository offerRepository) {

        this.currentUser = currentUser;
        this.userService = userService;
        this.modelService = modelService;
        this.mapper = mapper;
        this.offerRepository = offerRepository;
    }

    @Override
    public void saveOffer(OfferServiceModel offerServiceModel) {
        UserEntity seller = this.userService.findUserBy(this.currentUser.getId());
        ModelEntity model = this.modelService.findModelByName(offerServiceModel.getModel());

        OfferEntity offer = this.mapper.map(offerServiceModel, OfferEntity.class);
        offer.setSeller(seller);
        offer.setModel(model);

        this.offerRepository.save(offer);
    }
}
