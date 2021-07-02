import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class DiabloGames {
    private static final String SQL =
            "SELECT u.`id`, `first_name`, `last_name`, COUNT(ug.`id`) as 'count' " +
            "FROM `users` AS u " +
            "JOIN `users_games` AS ug ON u.`id` = ug.`user_id` " +
            "WHERE u.`user_name` = ?";

    public static void main(String[] args) {

        // 1. Read props from external property file
        Properties props = new Properties();
        String path = Objects.requireNonNull(DiabloGames.class.getClassLoader().
                getResource("jdbc.properties")).getPath();
        System.out.println("Resource path: " + path);

        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: add meaningful default
        System.out.println(props);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username (<Enter> for 'Alex'): ");
        String username = scanner.nextLine().trim();
        username = username.equals("") ? "Alex" : username;

        // 2. try-with-resources - Connection, PreparedStatement
        try (Connection connection = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

        // 3. Print results
            while (rs.next()) {
                if (rs.getLong("id") == 0L) {
                    System.out.println("No such user exists");
                    break;
                } else {
                    System.out.printf("%s %s has played %d games",
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
