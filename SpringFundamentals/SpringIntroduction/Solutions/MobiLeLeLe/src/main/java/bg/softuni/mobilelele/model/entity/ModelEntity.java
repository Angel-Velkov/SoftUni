package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.enums.CategoryEnum;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "models")
public class ModelEntity extends LifecycleEventEntity {

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum category;

    @NonNull
    @Column(nullable = false, length = 512)
    private String imageUrl;

    @NonNull
    @Column(nullable = false)
    private int startYear;

    private int endYear;

    @NonNull
    @ManyToOne(optional = false)
    private BrandEntity brand;

}
