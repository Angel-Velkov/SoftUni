package com.example.pathfinder.repository;

import com.example.pathfinder.model.entity.RoleEntity;
import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(RoleNameEnum role);
}
