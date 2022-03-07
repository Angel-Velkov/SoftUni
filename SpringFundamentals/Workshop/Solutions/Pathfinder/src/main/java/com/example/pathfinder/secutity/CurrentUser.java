package com.example.pathfinder.secutity;

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
    private Long id;
    private String username;
}
