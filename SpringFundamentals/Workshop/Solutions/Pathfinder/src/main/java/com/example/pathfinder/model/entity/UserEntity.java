package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    private Integer age;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<RoleEntity> role;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;
}
