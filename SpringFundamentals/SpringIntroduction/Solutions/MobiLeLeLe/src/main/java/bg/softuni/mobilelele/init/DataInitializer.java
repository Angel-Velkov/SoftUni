package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DataInitializer(UserRoleRepository userRoleRepository) {
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
