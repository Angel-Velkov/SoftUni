import entities.User;
import orm.core.Connector;
import orm.core.EntityManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, URISyntaxException, ClassNotFoundException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String username = "root";
        String password = "1234";
        String db = "test_schema";

        Connector.createConnection(username, password, db);

        EntityManager<User> em = new EntityManager<>(Connector.getConnection());
        em.createTables(Main.class);


        User testUser = new User("acho007", "strongPass", 20);
        em.persist(testUser);

        // It does not fill the registrationDate with the whole data (hours:minutes:seconds are lost).
        User found = em.findFirst(User.class, "age > 2");
        System.out.println(found);
    }
}