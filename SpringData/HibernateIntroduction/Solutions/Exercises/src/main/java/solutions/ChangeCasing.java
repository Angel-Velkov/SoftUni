package solutions;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Town> towns = em.createQuery(
                "SELECT t FROM Town AS t WHERE LENGTH(t.name) <= 5", Town.class
        ).getResultList();

        em.getTransaction().begin();
        towns.forEach(em::detach);

        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }

        towns.forEach(em::merge);
        em.flush();

        em.getTransaction().commit();
        em.close();
    }
}