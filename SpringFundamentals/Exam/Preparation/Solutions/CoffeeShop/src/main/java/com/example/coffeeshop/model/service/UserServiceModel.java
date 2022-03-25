package com.example.coffeeshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserServiceModel {
    private Long id;
    private String username;
    private String firstname;
    private String lastName;
    private String email;
    private String password;
}
