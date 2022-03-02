package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserRegisterBindingModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/register")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String register() {
        return "auth-register";
    }

    @PostMapping
    public String register(@ModelAttribute UserRegisterBindingModel userRegisterDto){

        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return "auth-register";
        }

        UserRegisterServiceModel serviceModel = this.modelMapper.map(userRegisterDto, UserRegisterServiceModel.class);

        this.userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }
}
