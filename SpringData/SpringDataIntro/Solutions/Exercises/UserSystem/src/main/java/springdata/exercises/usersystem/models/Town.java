package springdata.exercises.usersystem.models;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {
    private long id;
    private String name;
    private Country country;

    public Town() {
    }

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
