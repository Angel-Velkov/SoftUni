package orm.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/";
    private static Connection connection;

    public static void createConnection(String username, String password, String dbName) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        connection = DriverManager.getConnection(URL_CONNECTION + dbName, props);
    }

    public static Connection getConnection() {
        return connection;
    }
}