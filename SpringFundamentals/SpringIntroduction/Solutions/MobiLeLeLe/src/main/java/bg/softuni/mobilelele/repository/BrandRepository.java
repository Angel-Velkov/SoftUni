package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @Query("SELECT b.name, m.name FROM BrandEntity AS b JOIN b.models AS m")
    List<Object[]> findAllBrandWithTheirModels();

}
