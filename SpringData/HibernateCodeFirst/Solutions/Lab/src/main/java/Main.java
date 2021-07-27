import entitties.vehicles.Car;
import entitties.PlateNumber;
import entitties.vehicles.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vehicles");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Car car = new Car("Audi A8", new BigDecimal(5_500), "diesel", 5);
        Truck truck = new Truck("Fuso Canter", new BigDecimal(120_000), "gasoline", 5.5);
        PlateNumber plateNumber = new PlateNumber("C3020MH", car);

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.persist(truck);
        entityManager.persist(plateNumber);
        entityManager.getTransaction().commit();

        Car foundedCar = entityManager.find(Car.class, 1L);
        System.out.println("Found " + foundedCar);

        Truck foundedTruck = entityManager.find(Truck.class, 2L);
        System.out.println("Found " + foundedTruck);
    }
}