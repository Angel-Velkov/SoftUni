package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.dto.service.UserLoginServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setIsActive(true);
        this.userRepository.save(user);
        return user;
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<UserEntity> userEntity =
                this.userRepository.findByUsername(loginServiceModel.getUsername());

        if (userEntity.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(
                    loginServiceModel.getRawPassword(),
                    userEntity.get().getPassword()
            );

            if (success) {
                UserEntity loggedInUser = userEntity.get();

                currentUser
                        .setLoggedIn(true)
                        .setFirstName(loggedInUser.getFirstName())
                        .setLastName(loggedInUser.getLastName())
                        .setUsername(loggedInUser.getUsername());
            }

            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.clean();
    }
}
