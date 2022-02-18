package bg.softuni.mobilelele.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brands")
public class BrandEntity extends LifecycleEventEntity {

    @NonNull
    @Column(nullable = false)
    private String name;
}
