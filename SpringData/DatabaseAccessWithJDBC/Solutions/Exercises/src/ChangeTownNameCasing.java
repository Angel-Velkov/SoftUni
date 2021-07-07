import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChangeTownNameCasing {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    private static final String UPDATE_TOWNS_TO_UPPERCASE_SQL =
            "UPDATE `towns` " +
                    "SET `name` = UPPER(`name`) " +
                    "WHERE `country` = ?";

    private static final String SELECT_TOWNS_FROM_COUNTRY_SQL =
            "SELECT `name`" +
                    "FROM `towns` " +
                    "WHERE `country` = ?";

    public static void main(String[] args) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + DB_DRIVER + " not found!");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement changeTownNameToUppercase = connection.prepareStatement(UPDATE_TOWNS_TO_UPPERCASE_SQL);
             PreparedStatement getUpdatedTowns = connection.prepareStatement(SELECT_TOWNS_FROM_COUNTRY_SQL)) {
            String country = reader.readLine();
            changeTownNameToUppercase.setString(1, country);
            getUpdatedTowns.setString(1, country);

            changeTownNameToUppercase.executeUpdate();
            ResultSet rs = getUpdatedTowns.executeQuery();

            List<String> updatedTowns = new ArrayList<>();
            if (!rs.next()) {
                System.out.println("No town names were affected.");
            } else {
                do {
                    updatedTowns.add(rs.getString("name"));
                } while (rs.next());
                System.out.printf("%d town names were affected.%n" +
                        "[%s]", updatedTowns.size(), String.join(", ", updatedTowns));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}