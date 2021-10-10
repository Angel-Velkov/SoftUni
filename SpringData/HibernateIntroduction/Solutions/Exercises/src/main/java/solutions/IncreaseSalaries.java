package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<String> departmentNames = List.of("Engineering", "Tool Design", "Marketing", "Information Services");

        List<Employee> employees = em.createQuery("SELECT e " +
                        "FROM Employee AS e " +
                        "WHERE e.department.name IN (:departmentNames)", Employee.class)
                .setParameter("departmentNames", departmentNames)
                .getResultList();

        em.getTransaction().begin();
        employees.forEach(em::detach);

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
        }

        employees.forEach(em::merge);
        em.flush();

        em.getTransaction().commit();

        StringBuilder output = new StringBuilder();

        employees.forEach(e ->
                output.append(String.format("%s %s ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()))
        );

        System.out.println(output);

        emf.close();
        em.close();
    }
}