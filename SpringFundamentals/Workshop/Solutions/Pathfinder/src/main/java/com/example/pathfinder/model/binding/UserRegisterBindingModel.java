package com.example.pathfinder.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterBindingModel {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Positive
    @Max(90)
    private Integer age;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String confirmPassword;
}
