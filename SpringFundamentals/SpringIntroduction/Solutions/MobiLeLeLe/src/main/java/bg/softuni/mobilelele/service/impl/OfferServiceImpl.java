package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.exception.ObjectNotFoundException;
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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        ModelEntity model = this.modelService.findModelByName(offerServiceModel.getModelName());

        OfferEntity offer = this.mapper.map(offerServiceModel, OfferEntity.class);
        offer.setSeller(seller);
        offer.setModel(model);

        this.offerRepository.save(offer);
    }

    @Override
    public List<OfferServiceModel> findAllOffers() {
        return this.offerRepository.findAll()
                .stream()
                .map(offer -> this.mapper.map(offer, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OfferServiceModel findById(Long id) {
        return this.mapper.map(
                this.offerRepository.findById(id).orElse(null),
                OfferServiceModel.class
        );
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Modifying
    @Transactional
    @Override
    public void updateOffer(OfferServiceModel offerServiceModel) {
        OfferEntity offerEntity =
                this.offerRepository.findById(offerServiceModel.getId())
                        .orElseThrow(
                                () -> new ObjectNotFoundException("There is no offer with ID: " + offerServiceModel.getId())
                        );

        offerEntity.setModel(this.modelService.findModelByName(offerServiceModel.getModelName()));
        offerEntity.setPrice(offerServiceModel.getPrice());
        offerEntity.setEngine(offerServiceModel.getEngine());
        offerEntity.setTransmission(offerServiceModel.getTransmission());
        offerEntity.setYear(offerServiceModel.getYear());
        offerEntity.setMileage(offerServiceModel.getMileage());
        offerEntity.setDescription(offerServiceModel.getDescription());
        offerEntity.setImageUrl(offerServiceModel.getImageUrl());

        this.offerRepository.save(offerEntity);
    }
}
