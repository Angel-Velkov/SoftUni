package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String departmentName = "Research and Development";

        List<Employee> employees = em.createQuery("SELECT e " +
                "FROM Employee AS e " +
                "WHERE e.department.name = ?1 " +
                "ORDER BY e.salary, e.id", Employee.class)
                .setParameter(1, departmentName)
                .getResultList();

        StringBuilder output = new StringBuilder();
        employees.forEach(e ->
                output.append(String.format("%s %s from %s - %.2f%n",
                        e.getFirstName(), e.getLastName(), departmentName, e.getSalary()))
        );

        System.out.println(output);

        em.close();
    }
}