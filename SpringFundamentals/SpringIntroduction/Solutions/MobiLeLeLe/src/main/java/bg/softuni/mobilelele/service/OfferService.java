package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.OfferServiceModel;

import java.security.Principal;
import java.util.List;

public interface OfferService {

    void saveOffer(OfferServiceModel offerServiceModel, Principal principal);

    List<OfferServiceModel> findAllOffers();

    OfferServiceModel findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(OfferServiceModel offerServiceModel);
}
