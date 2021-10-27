package springdata.exercises.usersystem.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.exercises.usersystem.exceptions.NonExistentUserException;
import springdata.exercises.usersystem.models.Country;
import springdata.exercises.usersystem.models.Town;
import springdata.exercises.usersystem.models.User;
import springdata.exercises.usersystem.services.UserService;

@Component
@Slf4j
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            User acho = this.userService.createAccount("acho007", "12!Kdaw", "angel@kom.pot", (byte) 26);
            User pepi = this.userService.createAccount("peter_veliki", "pat1Ad?an", "pepi-rabivacha21@tam.pon", (byte) 69);

            this.userService.befriend(acho.getId(), pepi.getId());


            User accountById = this.userService.getAccountById(2);

            this.userService.setBornTown(2, new Town("Sofia", new Country("Bulgaria")));

            for (User friend : accountById.getFriends()) {
                System.out.println(accountById.getUsername() + " -> " + friend.getUsername());
            }

        } catch (IllegalArgumentException | NonExistentUserException e) {
            log.error(e.getMessage());
        }
    }
}
