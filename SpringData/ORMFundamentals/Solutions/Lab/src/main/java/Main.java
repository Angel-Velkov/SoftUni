import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {

        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine().trim();
        String password = scanner.nextLine().trim();
        String db = scanner.nextLine().trim();

        Connector.createConnection(username, password, db);
        EntityManager<User> em = new EntityManager<>(Connector.getConnection());

        User testUser = new User("root", "1234", 2, new Date());
        em.persist(testUser);
        User found = em.findFirst(User.class, "age > 18");
    }
}
