package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.binding.UserRegisterBindingModel;
import bg.softuni.mobilelele.model.service.UserServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @ModelAttribute
    private UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    private UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String getRegister() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", userRegisterBindingModel);

            return "redirect:register";
        }

        userService.registerUser(mapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes
    ) {

        redirectAttributes
                .addFlashAttribute("bad_credentials", true)
                .addFlashAttribute("name", username);

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
