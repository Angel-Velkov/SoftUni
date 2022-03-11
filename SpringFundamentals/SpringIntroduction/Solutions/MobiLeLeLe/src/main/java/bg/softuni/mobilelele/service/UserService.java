package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    boolean containsUser(String username, String password);

    void login(UserServiceModel userServiceModel);
}
