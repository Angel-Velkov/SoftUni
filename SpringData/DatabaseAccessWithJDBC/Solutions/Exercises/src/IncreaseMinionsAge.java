import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public static void main(String[] args) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + DB_DRIVER + " not found.");
        }

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement ps = connection.prepareStatement(UPDATE_INCREASE_AGE_BY_ONE_SQL)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int[] ids = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            StringBuilder output = new StringBuilder();
            for (int id : ids) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}