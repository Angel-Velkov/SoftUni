package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.UserServiceModel;

public interface UserService {

    boolean isUsernameFree(String username);

    boolean containsUser(String username, String password);

    UserEntity findUserByUsername(String username);

    void registerUser(UserServiceModel userServiceModel);
}
