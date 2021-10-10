package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String departmentName = "Research and Development";

        StringBuilder output = new StringBuilder();

        em.createQuery("SELECT e " +
                "FROM Employee AS e " +
                "WHERE e.department.name = ?1 " +
                "ORDER BY e.salary, e.id", Employee.class)
                .setParameter(1, departmentName)
                .getResultStream()
                .forEach(e ->
                        output.append(String.format("%s %s from %s - %.2f%n",
                                e.getFirstName(), e.getLastName(), departmentName, e.getSalary()))
                );

        System.out.println(output);

        em.close();
    }
}