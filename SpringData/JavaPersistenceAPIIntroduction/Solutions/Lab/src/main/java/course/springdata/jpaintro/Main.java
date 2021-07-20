package course.springdata.jpaintro;

import course.springdata.jpaintro.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();
        /// String name = reader.readLine();
        Student student = new Student("Angel");
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();

        em.close();
    }
}
