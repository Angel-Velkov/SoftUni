import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class AddMinion {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    public static void main(String[] args) throws IOException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + DB_DRIVER + " not found!");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Connection connection = getConnection(reader);

            String[] minionData = reader.readLine().split(" ");
            String minionName = minionData[0];
            int age = Integer.parseInt(minionData[1]);
            String townName = minionData[2];
            saveTownIfItDoesNotExist(townName, connection);

            addMinion(minionName, age, townName, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveTownIfItDoesNotExist(String townName, Connection connection) {
        long townId = getTownId(townName, connection);
    }

    private static void addMinion(String minionName, int age, String townName, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO `minions` (`name`, age, town_id)" +
                            "VALUES (?, ?, ?);");
            ps.setString(1, minionName);
            ps.setInt(2, age);
            ps.setString(3, townName);

            ps.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private static Connection getConnection(BufferedReader reader) throws IOException, SQLException {
        System.out.println("Enter user:");
        String user = reader.readLine().trim();
        user = user.length() < 1 ? "root" : user;

        System.out.println("Enter password:");
        String password = reader.readLine().trim();
        password = password.length() < 4 ? "Q7qR+sMf,S=.4n$u" : password;

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        return DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
    }

    private static long getTownId(String townName, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT `id` " +
                            "FROM `towns` " +
                            "WHERE `name` = ?;");
            ps.setString(1, townName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0L;
    }
}
