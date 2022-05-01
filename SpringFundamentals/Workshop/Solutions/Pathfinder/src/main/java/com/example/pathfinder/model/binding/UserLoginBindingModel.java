package com.example.pathfinder.model.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginBindingModel {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
