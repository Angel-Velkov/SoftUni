package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.UserLoginBindingModel;
import com.example.coffeeshop.model.binding.UserRegisterBindingModel;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult
                    );

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            bindingResult.addError(
                    new FieldError("userRegisterBindingModel", "confirmPassword", "The passwords do not match")
            );

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult
                    );

            return "redirect:register";
        }

        this.userService.register(
                this.mapper.map(userRegisterBindingModel, UserServiceModel.class)
        );

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("isExist")) {
            model.addAttribute("isExist", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult
                    );

            return "redirect:login";
        }

        boolean userExists = this.userService
                .containsUser(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!userExists) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("isExist", false)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult
                    );

            return "redirect:login";
        }

        this.userService.login(
                this.mapper.map(userLoginBindingModel, UserServiceModel.class)
        );

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        this.userService.logout();

        return "redirect:/";
    }
}
