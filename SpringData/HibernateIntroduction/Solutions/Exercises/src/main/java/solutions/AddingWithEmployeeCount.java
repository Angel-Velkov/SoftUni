package solutions;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddingWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Address> addresses = em.createQuery("SELECT a " +
                "FROM Address AS a " +
                "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : addresses) {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
        }
        em.close();
    }
}