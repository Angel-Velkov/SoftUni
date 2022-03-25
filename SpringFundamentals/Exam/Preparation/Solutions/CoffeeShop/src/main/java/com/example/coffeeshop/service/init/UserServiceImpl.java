package com.example.coffeeshop.service.init;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.EmployeeViewModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        this.userRepository.save(
                this.mapper.map(userServiceModel, UserEntity.class)
        );
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
            return password.equals(user.getPassword());
        }
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        this.currentUser.setUsername(userServiceModel.getUsername());
    }

    @Override
    public void logout() {
        this.currentUser.clear();
    }

    @Override
    public UserEntity getCurrentLoggedInUser() {
        return this.userRepository
                .findByUsername(this.currentUser.getUsername())
                .orElse(null);
    }

    @Override
    public List<EmployeeViewModel> findAllEmployees() {
        return this.userRepository.findAllEmployees();
    }
}
