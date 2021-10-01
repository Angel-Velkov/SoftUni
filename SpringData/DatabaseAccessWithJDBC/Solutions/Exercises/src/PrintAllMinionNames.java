import java.sql.*;
import java.util.Properties;

public class PrintAllMinionNames {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    private static final String SELECT_MINION_NAMES_SQL = "SELECT `name` FROM `minions` ORDER BY `id`";

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
             Statement getMinionNames = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = getMinionNames.executeQuery(SELECT_MINION_NAMES_SQL);
            rs.last();
            int size = rs.getRow();

            StringBuilder output = new StringBuilder();

            for (int i = 1; i <= (size / 2); i++) {
                rs.absolute(i);
                output.append(rs.getString("name"))
                        .append(System.lineSeparator());
                rs.absolute(i * (-1));
                output.append(rs.getString("name"))
                        .append(System.lineSeparator());
            }

            // If the records are odd it takes the last line (in the middle).
            if (size % 2 == 1) {
                rs.previous();
                output.append(rs.getString("name"))
                        .append(System.lineSeparator());
            }

            System.out.println(output.toString().trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}