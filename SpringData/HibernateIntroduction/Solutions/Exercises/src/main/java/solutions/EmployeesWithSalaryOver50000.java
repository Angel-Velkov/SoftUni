package solutions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<String> employeesFirstName = em.createQuery(
                "SELECT e.firstName " +
                        "FROM Employee AS e " +
                        "WHERE e.salary > 50000", String.class)
                .getResultList();

        employeesFirstName.forEach(System.out::println);

        em.close();
    }
}