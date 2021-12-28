package softuni.exam.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne(optional = false)
    private Picture picture;
}
