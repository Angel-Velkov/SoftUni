import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String TABLE_NAME = "/minions_db";

    public static void main(String[] args) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter user:");
        String user = reader.readLine();
        user = user.trim().equals("") ? "root" : user;

        System.out.println("Enter password:");
        String password = reader.readLine();
        password = password.length() < 4 ? "Q7qR+sMf,S=.4n$u" : password;

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, props)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
