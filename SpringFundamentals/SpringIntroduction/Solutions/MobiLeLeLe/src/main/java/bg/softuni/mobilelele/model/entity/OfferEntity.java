package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "offers")
public class OfferEntity extends LifecycleEventEntity {

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    @Column(nullable = false)
    private EngineEnum engine;

    @NonNull
    @Column(nullable = false, length = 512)
    private String imageUrl;

    @NonNull
    private Integer mileage;

    @NonNull
    @Column()
    private Integer price;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private TransmissionEnum transmission;

    private Integer year;

    @NonNull
    @ManyToOne(optional = false)
    private ModelEntity model;

    @ManyToOne()
    private UserEntity seller;
}
