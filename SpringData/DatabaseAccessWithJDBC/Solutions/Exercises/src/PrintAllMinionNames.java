import java.sql.*;
import java.util.Properties;

public class PrintAllMinionNames {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    private static final String SELECT_MINION_NAMES_SQL = "SELECT `name` FROM `minions`";

    public static void main(String[] args) {
        try{
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + DB_DRIVER + " not found.");
        }

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement getMinionNames = connection.prepareStatement(SELECT_MINION_NAMES_SQL)) {
            ResultSet rs = getMinionNames.executeQuery();

            StringBuilder output = new StringBuilder();

            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
