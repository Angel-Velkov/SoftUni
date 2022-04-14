package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    void saveOffer(OfferServiceModel offerServiceModel, String username);

    List<OfferServiceModel> findAllOffers();

    OfferServiceModel findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(OfferServiceModel offerServiceModel);

    boolean hasAccess(String username, Long offerId);
}
