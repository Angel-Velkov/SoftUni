package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ContainsEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter full name:");
        String[] fullName = reader.readLine().trim().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees = em.createQuery("SELECT e " +
                "FROM Employee AS e " +
                "WHERE e.firstName = :firstName " +
                "AND e.lastName = :lastName", Employee.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();

        System.out.println(employees.isEmpty() ? "No" : "Yes");

        em.close();
    }
}