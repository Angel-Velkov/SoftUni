package hiberspring.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Town town;

    @OneToMany(mappedBy = "branch")
    private Set<Product> products;
}
