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
@Table(name = "users")
public class UserEntity extends LifecycleEventEntity {

    @NonNull
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String lastName;

    private Boolean isActive;

    @Column(nullable = false)
    @ManyToMany
    private Set<UserRoleEntity> role = new HashSet<>();

    private String imageUrl;
}
