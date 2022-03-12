package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.model.service.UserServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, CurrentUser currentUser, ModelMapper mapper) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        UserEntity user = this.mapper.map(userServiceModel, UserEntity.class);
        String encode = this.passwordEncoder.encode(userServiceModel.getPassword().trim());
        user.setPassword(encode);
        user.setIsActive(false);

        RoleEnum roleEnum = userServiceModel.getRole();
        Set<UserRoleEntity> roles = new HashSet<>();

        switch (roleEnum) {
            case ADMIN:
                roles.add(this.userRoleRepository.findByRole(RoleEnum.ADMIN));
            case USER:
                roles.add(this.userRoleRepository.findByRole(RoleEnum.USER));
        }

        user.setRoles(roles);
        this.userRepository.save(user);

        return userServiceModel;
    }

    @Override
    public boolean containsUser(String username, String password) {
        UserEntity user = this.userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return false;
        } else {
            return this.passwordEncoder.matches(password, user.getPassword());
        }
    }

    @Modifying
    @Override
    public void login(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository
                .findByUsername(userServiceModel.getUsername())
                .orElseThrow(() -> new IllegalStateException("There is no such username"));

        user.setIsActive(true);
        this.userRepository.save(user);

        this.currentUser.setId(user.getId());
        this.currentUser.setUsername(user.getUsername());
    }

    @Modifying
    @Override
    public void logout() {
        UserEntity user = this.userRepository
                .findById(this.currentUser.getId())
                .orElseThrow(() -> new IllegalStateException("There is no such logged in user"));

        user.setIsActive(false);
        this.userRepository.saveAndFlush(user);

        this.currentUser.clean();
    }
}
