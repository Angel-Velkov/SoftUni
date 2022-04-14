package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    @Query("SELECT CASE WHEN COUNT (o) > 0 THEN true ELSE false END " +
            "FROM OfferEntity o " +
            "WHERE o.id = :offerId " +
            "   AND o.seller.username = :username")
    boolean areRelated(String username, Long offerId);
}
