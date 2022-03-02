package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    public DataInitializer(UserService userService, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity user = new UserRoleEntity(RoleEnum.USER);
            UserRoleEntity admin = new UserRoleEntity(RoleEnum.ADMIN);

            this.userRoleRepository.save(user);
            this.userRoleRepository.save(admin);
        }
    }
}
