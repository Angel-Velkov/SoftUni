package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    boolean isUsernameFree(String username);

    boolean containsUser(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    UserEntity findUserBy(Long id);
}
