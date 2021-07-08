import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";
    private static final String SQL =
            "SELECT `id`, `name`, COUNT(DISTINCT `minion_id`) AS 'minions_count' " +
                    "FROM `villains` " +
                    "JOIN `minions_villains` ON `id` = `villain_id` " +
                    "GROUP BY `id`" +
                    "HAVING `minions_count` > ? " +
                    "ORDER BY `minions_count` DESC;";

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
        password = password.length() < 4 ? "1234" : password;

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, 15);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.printf("%s %d%n",
                        rs.getString("name"),
                        rs.getInt("minions_count"));
            }

            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing DB connection " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}