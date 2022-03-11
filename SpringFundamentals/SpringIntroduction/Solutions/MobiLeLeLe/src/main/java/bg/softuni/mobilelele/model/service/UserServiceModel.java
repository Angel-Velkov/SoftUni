package bg.softuni.mobilelele.model.service;

import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserServiceModel {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private RoleEnum role;
    private String imageUrl;
}
