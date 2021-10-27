package springdata.exercises.usersystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.exercises.usersystem.exceptions.NonExistentUserException;
import springdata.exercises.usersystem.models.Town;
import springdata.exercises.usersystem.models.User;
import springdata.exercises.usersystem.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createAccount(String username, String password, String email, byte age) {
        User user = new User(username, password, email, age);

        this.userRepository.save(user);

        return user;
    }

    @Override
    public User getAccountById(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NonExistentUserException(id));
    }

    @Override
    public void setBornTown(long userId, Town town) {
        User user = this.getAccountById(userId);

        user.setBornTown(town);
    }

    @Override
    public void setCurrentlyLivingTown(long userId, Town town) {
        User user = this.getAccountById(userId);

        user.setCurrentlyLiving(town);
    }

    @Override
    public void befriend(long firstId, long secondId) {
        if (firstId == secondId) {
            throw new IllegalArgumentException("You cannot make friends with yourself...");
        }

        User firstUser = this.userRepository.findById(firstId).orElseThrow(() -> new NonExistentUserException(firstId));
        User secondUser = this.userRepository.findById(secondId).orElseThrow(() -> new NonExistentUserException(secondId));

        firstUser.getFriends().add(secondUser);
        secondUser.getFriends().add(firstUser);
    }
}
