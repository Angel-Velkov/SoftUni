package softuni.exam.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.domain.enums.Position;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, length = 15)
    private String lastName;

    @Column(nullable = false)
    private Byte number;

    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @ManyToOne(optional = false)
    private Picture picture;

    @ManyToOne(optional = false)
    private Team team;
}
