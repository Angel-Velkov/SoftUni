package com.example.coffeeshop.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginBindingModel {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3)
    private String password;
}
