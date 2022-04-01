package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserEntity;

public interface UserService {

    boolean isUsernameFree(String username);

    boolean containsUser(String username, String password);

    UserEntity findUserBy(Long id);
}
