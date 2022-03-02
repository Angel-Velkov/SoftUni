package bg.softuni.mobilelele.model.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterBindingModel {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
}
