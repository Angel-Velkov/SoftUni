package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.dto.service.UserLoginServiceModel;

public interface UserService {

    UserEntity createUser(UserEntity user);

    boolean login(UserLoginServiceModel loginServiceModel);

    void logout();
}
