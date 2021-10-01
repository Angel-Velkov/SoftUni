import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class IncreaseMinionsAge {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    private static final String UPDATE_INCREASE_AGE_BY_ONE_SQL =
            "UPDATE `minions` " +
                    "SET `age` = `age` + 1, `name` = LOWER(`name`) " +
                    "WHERE `id` = ?";

    public static final String SELECT_MINIONS = "SELECT `id`, `name`, `age` FROM minions;";

    public static void main(String[] args) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + DB_DRIVER + " not found.");
            System.exit(-1);
        }

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement increaseAge = connection.prepareStatement(UPDATE_INCREASE_AGE_BY_ONE_SQL);
             Statement getMinions = connection.createStatement()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int[] ids = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            for (int id : ids) {
                increaseAge.setInt(1, id);
                increaseAge.executeUpdate();
            }

            StringBuilder output = new StringBuilder();

            ResultSet minions = getMinions.executeQuery(SELECT_MINIONS);
            while (minions.next()) {
                output.append(String.format("%5d | %-15.15s | %d%n",
                        minions.getLong("id"),
                        minions.getString("name"),
                        minions.getInt("age")));
            }

            System.out.println(output);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}