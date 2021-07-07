import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class RemoveVillain {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    private static final String DELETE_FROM_MINIONS_VILLAINS_SQL = "DELETE FROM `minions_villains` WHERE `villain_id` = ?";
    private static final String DELETE_FROM_VILLAINS_SQL = "DELETE FROM `villains` WHERE `id` = ?";
    private static final String SELECT_VILLAIN_NAME_SQL = "SELECT `name` FROM `villains` WHERE `id` = ?";

    public static void main(String[] args) throws IOException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + DB_DRIVER + " not found.");
        }

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
             PreparedStatement deleteFromMinionsVillains = connection.prepareStatement(DELETE_FROM_MINIONS_VILLAINS_SQL);
             PreparedStatement deleteFromVillains = connection.prepareStatement(DELETE_FROM_VILLAINS_SQL);
             PreparedStatement getVillainName = connection.prepareStatement(SELECT_VILLAIN_NAME_SQL)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            long id = Long.parseLong(reader.readLine());
            deleteFromMinionsVillains.setLong(1, id);
            deleteFromVillains.setLong(1, id);
            getVillainName.setLong(1, id);

            try {
                connection.setAutoCommit(false);
                ResultSet rs = getVillainName.executeQuery();
                if (rs.next()) {
                    String deletedVillain = rs.getString("name");
                    int releasedMinions = deleteFromMinionsVillains.executeUpdate();
                    deleteFromVillains.executeUpdate();

                    connection.commit();
                    System.out.printf("%s was deleted%n" +
                            "%d minions released", deletedVillain, releasedMinions);
                } else {
                    System.out.println("No such villain was found");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}