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
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer population;
}
