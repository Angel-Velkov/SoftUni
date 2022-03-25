package com.example.coffeeshop.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@NoArgsConstructor
@Getter
@Setter
@Component
@SessionScope
public class CurrentUser {
    private String username;

    public void clear() {
        this.username = null;
    }
}
