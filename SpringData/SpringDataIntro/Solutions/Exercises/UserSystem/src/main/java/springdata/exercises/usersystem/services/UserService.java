package springdata.exercises.usersystem.services;

import springdata.exercises.usersystem.models.Town;
import springdata.exercises.usersystem.models.User;

public interface UserService {
    User createAccount(String username, String password, String email, byte age);

    User getAccountById(long id);

    void setBornTown(long userId, Town town);

    void setCurrentlyLivingTown(long userId, Town town);

    void befriend(long first, long second);
}
