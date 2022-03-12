package bg.softuni.mobilelele.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "brand", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ModelEntity> models = new HashSet<>();
}
