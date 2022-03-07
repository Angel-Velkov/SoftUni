package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;
}
