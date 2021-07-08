import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetMinionNames {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";
    private static final String SQL =
            "SELECT v.`name`, m.`name`, m.`age` " +
                    "FROM `minions` AS m " +
                    "JOIN `minions_villains` mv ON m.id = mv.`minion_id` " +
                    "JOIN `villains` v on v.`id` = mv.`villain_id` " +
                    "WHERE v.`id` = ? " +
                    "ORDER BY m.`name`";

    public static void main(String[] args) throws IOException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("DB Driver " + DB_DRIVER + " not found!");
            System.exit(0);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter user:");
        String user = reader.readLine().trim();
        user = user.length() < 1 ? "root" : user;

        System.out.println("Enter password:");
        String password = reader.readLine().trim();
        password = password.length() < 4 ? "1234" : password;

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement ps = connection.prepareStatement(SQL)) {
            System.out.println("Enter villain ID:");
            long villainId = Long.parseLong(reader.readLine());
            ps.setLong(1, villainId);

            ResultSet rs = ps.executeQuery();
            StringBuilder output = new StringBuilder();
            if (!rs.next()) {
                output.append("No villain ID ").append(villainId).append(" exists in database.");
            } else {
                output.append("Villain: ").append(rs.getString("v.name"));
                long counter = 1L;

                do {
                    output.append(System.lineSeparator())
                            .append(String.format("%d. %s %d",
                                    counter++,
                                    rs.getString("m.name"),
                                    rs.getInt("age")));
                } while (rs.next());
            }
            System.out.println(output);
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