package com.example.automappingobjectsexercise;

import com.example.automappingobjectsexercise.model.dto.UserLoginDto;
import com.example.automappingobjectsexercise.model.dto.UserRegistrationDto;
import com.example.automappingobjectsexercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader reader;
    private final UserService userService;

    @Autowired
    public CommandLineRunnerImpl(BufferedReader reader, UserService userService) {
        this.reader = reader;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String[] commands = reader.readLine().split("\\|");
            String fullName;
            try {
                switch (commands[0]) {
                    case "RegisterUser" -> {
                        fullName = this.userService
                                .registerUser(
                                        new UserRegistrationDto(
                                                commands[1],
                                                commands[2],
                                                commands[3],
                                                commands[4]
                                        )
                                );
                        System.out.println(fullName + " was registered");
                    }
                    case "LoginUser" -> {
                        fullName = this.userService
                                .loginUser(
                                        new UserLoginDto(
                                                commands[1],
                                                commands[2]
                                        )
                                );
                        System.out.println("Successfully logged in " + fullName);
                    }
                    case "Logout" -> {
                        fullName = this.userService.logout();
                        System.out.println("User " + fullName + " successfully logged out");
                    }
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
