package solutions;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        System.out.println("Enter employee id:");
        int id = Integer.parseInt(reader.readLine());
        Employee employee = em.find(Employee.class, id);

        System.out.printf("%s %s - %s%n   %s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getProjects().stream()
                        .map(Project::getName)
                        .sorted()
                        .collect(Collectors.joining(System.lineSeparator() + "   ")));
        em.close();
    }
}