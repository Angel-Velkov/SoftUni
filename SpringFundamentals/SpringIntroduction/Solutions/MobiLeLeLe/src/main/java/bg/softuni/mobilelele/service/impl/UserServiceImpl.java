package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.model.service.UserServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper mapper,
                           UserDetailsService userDetailsService) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsername(username);
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

    @Override
    public UserEntity findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserRoleEntity userRole = userRoleRepository.findByRole(RoleEnum.USER);

        UserEntity userEntity = new UserEntity(
                userServiceModel.getUsername(), userServiceModel.getPassword(), userServiceModel.getFirstName(), userServiceModel.getLastName()
        );
        userEntity.setRoles(Set.of(userRole));

        userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userEntity.getPassword(), userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
