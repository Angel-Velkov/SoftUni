package hiberspring.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cards")
public class EmployeeCard extends BaseEntity {

    @Column(unique = true)
    private String number;
}
