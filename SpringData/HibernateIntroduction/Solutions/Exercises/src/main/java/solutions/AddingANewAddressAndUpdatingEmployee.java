package solutions;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String addressText = "Vitoshka 15";
        Address address = createAddress(em, addressText);

        String lastName = reader.readLine().trim();
        List<Employee> employees = em.createQuery(
                "SELECT e FROM Employee AS e WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName)
                .getResultList();

        em.getTransaction().begin();
        employees.forEach(e -> e.setAddress(address));
        em.getTransaction().commit();

        em.close();
    }

    private static Address createAddress(EntityManager em, String addressText) {
        Address address = new Address();
        address.setText(addressText);

        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();

        return address;
    }
}

// If you want to see the repeated last names and their count.
/*
USE soft_uni;

SELECT e1.last_name ,COUNT(*) FROM employees AS e1
WHERE e1.last_name IN
	(SELECT e2.last_name FROM employees AS e2 WHERE e1.employee_id NOT LIKE e2.employee_id)
GROUP BY e1.last_name;
 */