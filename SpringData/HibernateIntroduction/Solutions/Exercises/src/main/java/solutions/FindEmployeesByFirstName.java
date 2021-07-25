package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        System.out.println("Enter the name prefix:");
        String prefix = reader.readLine().trim() + "%";

        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :namePrefix", Employee.class)
                .setParameter("namePrefix", prefix)
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }

        em.close();
    }
}