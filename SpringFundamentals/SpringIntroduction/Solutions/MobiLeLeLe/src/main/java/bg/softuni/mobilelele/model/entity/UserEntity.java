package bg.softuni.mobilelele.model.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
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

    private String lastName;

    private boolean isActive;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> role;

    private String imageUrl;
}
