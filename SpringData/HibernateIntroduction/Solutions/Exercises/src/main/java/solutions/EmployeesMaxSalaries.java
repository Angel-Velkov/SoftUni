package solutions;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesMaxSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Department> departments = em.createQuery("SELECT d FROM Department AS d " +
                "JOIN Employee AS e ON d = e.department " +
                "GROUP BY e.department.id " +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000 ", Department.class)
                .getResultList();

        StringBuilder output = new StringBuilder();
        for (Department department : departments) {
            output.append(
                    String.format("%s %.2f%n",
                            department.getName(),
                            getDepartmentMaxSalary(department))
            );
        }

        System.out.println(output);

        em.close();
        emf.close();
    }

    private static double getDepartmentMaxSalary(Department department) {
        return department.getEmployees()
                .stream()
                .map(Employee::getSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .max()
                .orElse(0.0);
    }
}