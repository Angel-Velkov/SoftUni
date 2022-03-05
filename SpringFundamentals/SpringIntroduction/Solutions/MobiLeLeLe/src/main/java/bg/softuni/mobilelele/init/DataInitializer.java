package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public DataInitializer(UserService userService, UserRoleRepository userRoleRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity user = new UserRoleEntity(RoleEnum.USER);
            UserRoleEntity admin = new UserRoleEntity(RoleEnum.ADMIN);

            this.userRoleRepository.save(user);
            this.userRoleRepository.save(admin);

            BrandEntity ford = new BrandEntity("Ford");
            ModelEntity f180 = new ModelEntity("F180", CategoryEnum.TRUCK, "", 2013, ford);

            this.brandRepository.save(ford);
            this.modelRepository.save(f180);
        }
    }
}
