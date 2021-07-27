package entitties.vehicles;

import entitties.Company;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle {
    private Integer passengerCapacity;
    private Company company;

    public Plane() {
    }

    public Plane(String model, BigDecimal price, String fuelType, Integer passengerCapacity, Company company) {
        super(model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
        this.company = company;
    }

    @Column(name = "passenger_capacity")
    public Integer getPassengerCapacity() {
        return this.passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Plane:" + System.lineSeparator() +
                super.toString() + System.lineSeparator() +
                "   passengerCapacity = " + passengerCapacity;
    }
}