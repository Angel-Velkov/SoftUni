package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.model.view.BrandWithModelNamesViewModel;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    private final BrandService brandService;

    @Autowired
    public DataInitializer(UserRoleRepository userRoleRepository, BrandRepository brandRepository,
                           ModelRepository modelRepository, BrandService brandService) {

        this.userRoleRepository = userRoleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity user = new UserRoleEntity(RoleEnum.USER);
            UserRoleEntity admin = new UserRoleEntity(RoleEnum.ADMIN);

            this.userRoleRepository.save(user);
            this.userRoleRepository.save(admin);
        }

        if (this.brandRepository.count() == 0) {
            BrandEntity tesla = new BrandEntity("Tesla");
            BrandEntity bmw = new BrandEntity("BMW");
            BrandEntity ford = new BrandEntity("Ford");

            if (this.modelRepository.count() == 0) {
                //Tesla
                ModelEntity modelS = new ModelEntity("Model S", CategoryEnum.CAR, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/2018_Tesla_Model_S_75D.jpg/330px-2018_Tesla_Model_S_75D.jpg", 2012, tesla);
                ModelEntity model3 = new ModelEntity("Model 3", CategoryEnum.CAR, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/2019_Tesla_Model_3_Performance_AWD_Front.jpg/330px-2019_Tesla_Model_3_Performance_AWD_Front.jpg", 2016, tesla);
                ModelEntity modelX = new ModelEntity("Model X", CategoryEnum.CAR, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/2017_Tesla_Model_X_100D_Front.jpg/330px-2017_Tesla_Model_X_100D_Front.jpg", 2015, tesla);

                //BMW
                ModelEntity f90m5 = new ModelEntity("M5", CategoryEnum.CAR, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/BMW%2C_Techno_Classica_2018%2C_Essen_%28IMG_8995%29.jpg/420px-BMW%2C_Techno_Classica_2018%2C_Essen_%28IMG_8995%29.jpg", 1984, bmw);
                ModelEntity s2f44 = new ModelEntity("2 Series Gran Coupé (F44)", CategoryEnum.CAR, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/2020_BMW_M235i_xDrive_Gran_Coupe_Front.jpg/420px-2020_BMW_M235i_xDrive_Gran_Coupe_Front.jpg", 2020, bmw);
                ModelEntity x5 = new ModelEntity("X5", CategoryEnum.MOTORCYCLE, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/2019_BMW_X5_M50d_Automatic_3.0.jpg/420px-2019_BMW_X5_M50d_Automatic_3.0.jpg", 1999, bmw);

                //Ford
                ModelEntity f150 = new ModelEntity("F150", CategoryEnum.TRUCK, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2016_Ford_F-150_Limited_Super_Crew_3.5L%2C_front_6.29.19.jpg/330px-2016_Ford_F-150_Limited_Super_Crew_3.5L%2C_front_6.29.19.jpg", 1948, ford);
                ModelEntity bSeries = new ModelEntity("B series", CategoryEnum.BUSS, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/1980sThomasFordSheffield.jpg/420px-1980sThomasFordSheffield.jpg", 1948, 1998, ford);
                ModelEntity mustang = new ModelEntity("Mustang", CategoryEnum.CAR, "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Ford_Mustang_%281964%29_-_28540253076.jpg/420px-Ford_Mustang_%281964%29_-_28540253076.jpg", 1964, ford);
            }

            this.brandRepository.saveAll(List.of(tesla, bmw, ford));
        }
    }
}
