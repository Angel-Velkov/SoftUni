package com.example.coffeeshop.model.binding;

import com.example.coffeeshop.validator.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterBindingModel {

    @NotBlank
    @Size(min = 5, max = 20)
    @UniqueUsername
    private String username;

    private String firstName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;
}
