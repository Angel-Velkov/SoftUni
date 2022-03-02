package bg.softuni.mobilelele.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterServiceModel {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
