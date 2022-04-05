package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
