package com.example.pathfinder.init;

import com.example.pathfinder.model.entity.RoleEntity;
import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import com.example.pathfinder.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.roleRepository.count() == 0) {
            RoleEntity user = new RoleEntity(RoleNameEnum.USER);
            RoleEntity admin = new RoleEntity(RoleNameEnum.ADMIN);

            this.roleRepository.save(user);
            this.roleRepository.save(admin);
        }
    }
}
