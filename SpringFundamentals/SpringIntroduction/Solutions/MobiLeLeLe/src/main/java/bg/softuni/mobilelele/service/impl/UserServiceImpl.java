package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, CurrentUser currentUser) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        if (currentUser.isLoggedIn()){
            return true;
        }

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

                login(loggedInUser);
            }

            return success;
        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegistrationModel) {

        UserEntity newUser = new UserEntity(
                userRegistrationModel.getUsername(),
                this.passwordEncoder.encode(userRegistrationModel.getPassword()),
                userRegistrationModel.getFirstName(),
                userRegistrationModel.getLastName()
        );

        UserRoleEntity userRole = this.userRoleRepository.findByRole(RoleEnum.USER);
        newUser.getRole().add(userRole);

        this.userRepository.save(newUser);

        login(newUser);
    }

    private void login(UserEntity user) {
        this.currentUser
                .setLoggedIn(true)
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername());
    }

    @Override
    public void logout() {
        this.currentUser.clean();
    }
}
