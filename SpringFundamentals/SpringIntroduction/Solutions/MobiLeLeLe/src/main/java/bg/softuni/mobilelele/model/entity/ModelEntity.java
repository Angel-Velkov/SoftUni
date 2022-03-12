package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "models")
public class ModelEntity extends LifecycleEventEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false, length = 512)
    private String imageUrl;

    @Column(nullable = false)
    private Integer startYear;

    private Integer endYear;

    @ManyToOne(optional = false)
    private BrandEntity brand;

    public ModelEntity(String name, CategoryEnum category, String imageUrl, Integer startYear, BrandEntity brand) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.setBrand(brand);
    }

    public ModelEntity(String name, CategoryEnum category, String imageUrl, Integer startYear, Integer endYear, BrandEntity brand) {
        this(name,category,imageUrl,startYear,brand);
        this.endYear = endYear;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
        brand.getModels().add(this);
    }
}
