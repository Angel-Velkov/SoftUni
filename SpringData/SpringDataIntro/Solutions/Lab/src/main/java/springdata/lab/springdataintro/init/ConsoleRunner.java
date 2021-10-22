package springdata.lab.springdataintro.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import springdata.lab.springdataintro.models.Account;
import springdata.lab.springdataintro.models.User;
import springdata.lab.springdataintro.services.AccountService;
import springdata.lab.springdataintro.services.UserService;

import java.math.BigDecimal;

public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Martin", 24);

        Account account = new Account(new BigDecimal("25_000"), user);

        user.getAccounts().add(account);

        this.userService.register(user);

        this.accountService.withdrawMoney(new BigDecimal("15_000"), user.getId());
        this.accountService.depositMoney(new BigDecimal("5_000"), user.getId());
    }
}
