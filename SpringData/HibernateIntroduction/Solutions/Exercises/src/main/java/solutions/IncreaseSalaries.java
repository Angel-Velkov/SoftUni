package solutions;

import entities.Employee;
import org.hibernate.FlushMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        // I'm using their ids because if I'm using their names it doesn't allows me to update the employees.
        // I don't know hot to disable the auto-update mode.
        List<Integer> departmentIds = List.of(1, 2, 4, 11);

        em.getTransaction().begin();
        int affectedEmployees = em.createQuery("UPDATE Employee AS e " +
                "SET e.salary = e.salary * 1.12 " +
                "WHERE e.department.id IN (:departmentNames)")
                .setParameter("departmentNames", departmentIds)
                .executeUpdate();

        System.out.println("Affected employees: " + affectedEmployees);
        System.out.println("<=====================>");

        em.getTransaction().commit();

        List<Employee> employees = em.createQuery("SELECT e " +
                "FROM Employee AS e " +
                "WHERE e.department.id IN (:departmentNames)", Employee.class)
                .setParameter("departmentNames", departmentIds)
                .getResultList();

        StringBuilder output = new StringBuilder();
        employees.forEach(e ->
                output.append(String.format("%s %s ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()))
        );
        System.out.println(output);
        em.close();
    }
}