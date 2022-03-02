package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
