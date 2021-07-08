import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class IncreaseAgeStoredProcedure {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA_NAME = "minions_db";

    private static final String USP_GET_OLDER_SQL = "{CAll usp_get_older(?)}";

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
             CallableStatement cs = connection.prepareCall(USP_GET_OLDER_SQL)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int id = Integer.parseInt(reader.readLine());
            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("name") + " | " + rs.getInt("age"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}