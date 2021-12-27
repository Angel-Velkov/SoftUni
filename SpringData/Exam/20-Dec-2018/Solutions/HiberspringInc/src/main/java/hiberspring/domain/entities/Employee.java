package hiberspring.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String position;

    @OneToOne(optional = false)
    private EmployeeCard card;

    @ManyToOne(optional = false)
    private Branch branch;
}
