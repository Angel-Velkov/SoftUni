import entities.User;
import orm.core.Connector;
import orm.core.EntityManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, IOException, URISyntaxException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String username = "root";
        String password = "1234";
        String db = "test_schema";

        Connector.createConnection(username, password, db);
        // EntityManager.createTables(Connector.getConnection(), Main.class);

        EntityManager<User> em = new EntityManager<>(Connector.getConnection());

        User testUser = new User("acho007", "strongPass", 20, new Date());
        em.persist(testUser);
        User found = em.findFirst(User.class, "age > 18");
    }
}