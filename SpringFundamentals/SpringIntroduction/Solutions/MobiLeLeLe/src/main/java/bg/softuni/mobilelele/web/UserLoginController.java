package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.dto.service.UserLoginServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/login")
public class UserLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String login() {
        return "auth-login";
    }

    @PostMapping
    public String login(UserLoginBindingModel userLoginDto) {

        boolean isLogged = this.userService.login(
                new UserLoginServiceModel(
                        userLoginDto.getUsername(),
                        userLoginDto.getPassword()
                )
        );

        LOGGER.info("User tried to login. Username = {}. Success = {}",
                userLoginDto.getUsername(), isLogged);

        if (isLogged) {
            return "redirect:/index";
        }

        return "redirect:/users/login";
    }
}
