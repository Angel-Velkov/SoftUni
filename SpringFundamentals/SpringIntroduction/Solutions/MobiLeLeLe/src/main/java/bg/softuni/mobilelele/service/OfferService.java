package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    void saveOffer(OfferServiceModel offerServiceModel);

    List<OfferServiceModel> findAllOffers();

    OfferServiceModel findById(Long id);
}
