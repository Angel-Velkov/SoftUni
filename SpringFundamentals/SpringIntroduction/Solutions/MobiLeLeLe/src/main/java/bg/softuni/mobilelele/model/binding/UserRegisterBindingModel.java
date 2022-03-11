package bg.softuni.mobilelele.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterBindingModel {

    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotNull
    private String role;
}
