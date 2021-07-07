import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class AddMinion {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA_NAME = "/minions_db";

    private static final String TOWN_ID_SQL = "SELECT `id` FROM `towns` WHERE `name` = ?";
    private static final String ADD_TOWN_SQL = "INSERT INTO `towns` (`name`, `country`) VALUES (?, 'Unknown')";
    private static final String ADD_MINION_SQL = "INSERT INTO `minions` (`name`, `age`, `town_id`) VALUES (?, ?, ?)";
    private static final String ADD_VILLAIN_SQL = "INSERT INTO `villains` (`name`, `evilness_factor`) VALUES (?, 'evil')";
    private static final String ADD_MINION_TO_VILLAIN_SQL = "INSERT INTO `minions_villains` (`minion_id`, `villain_id`) VALUES (?, ?)";
    private static final String ID_OF_THE_LAST_ENTITY_SQL = "SELECT * FROM %s ORDER BY `id` DESC LIMIT 1";
    private static final String GET_ENTITY_NAME_SQL = "SELECT `name` FROM %s WHERE `id` = ?";

    private static Connection connection;

    public static void main(String[] args) throws IOException, SQLException {
        loadDriver(DB_DRIVER);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        StringBuilder sb = new StringBuilder();
        try {
            connection = DriverManager.getConnection(CONNECTION_URL + SCHEMA_NAME, props);
            connection.setAutoCommit(false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String[] minionData = Arrays.stream(reader.readLine().split("\\s+")).skip(1).toArray(String[]::new);
            String minionName = minionData[0];
            int age = Integer.parseInt(minionData[1]);
            String townName = minionData[2];

            long townId = getTownId(townName);

            if (townId == 0) {
                sb.append(addTown(townName))
                        .append(System.lineSeparator());
                townId = getTownId(townName);
            }

            addMinion(minionName, age, townId);
            long minionId = getTheIdOfTheLastEntity("minions");

            String[] villainData = Arrays.stream(reader.readLine().split("\\s+")).skip(1).toArray(String[]::new);
            String villainName = villainData[0];

            sb.append(addEvilVillain(villainName))
                    .append(System.lineSeparator());
            long villainId = getTheIdOfTheLastEntity("villains");

            sb.append(connectMinionToVillain(minionId, villainId))
                    .append(System.lineSeparator());

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        System.out.println(sb.toString().trim());
    }

    private static String connectMinionToVillain(long minionId, long villainId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(ADD_MINION_TO_VILLAIN_SQL);
        ps.setLong(1, minionId);
        ps.setLong(2, villainId);
        ps.executeUpdate();

        return "Successfully added " + getEntityName("minions", minionId)
                + " to be minion of " + getEntityName("villains", villainId);
    }

    private static String addEvilVillain(String villainName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(ADD_VILLAIN_SQL);
        ps.setString(1, villainName);
        ps.executeUpdate();

        return "Villain " + villainName + " was added to the database.";
    }

    private static void addMinion(String minionName, int age, long townId) throws SQLException {
        validateName(minionName);
        validateAge(age);

        PreparedStatement ps = connection.prepareStatement(ADD_MINION_SQL);
        ps.setString(1, minionName);
        ps.setInt(2, age);
        ps.setLong(3, townId);
        ps.executeUpdate();
    }

    private static void validateAge(int age) {
        if (0 > age || age > 120) {
            throw new IllegalArgumentException("The age should be in the range [0, 120]");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("The name {" + name + "} cannot be less than 3 characters long");
        }
    }

    private static String getEntityName(String tableName, long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(String.format(GET_ENTITY_NAME_SQL, tableName));
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("name");
        } else {
            return null;
        }
    }

    private static long getTheIdOfTheLastEntity(String tableName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                String.format(ID_OF_THE_LAST_ENTITY_SQL, tableName));
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getLong("id");
        } else {
            return 0L;
        }
    }

    private static long getTownId(String townName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(TOWN_ID_SQL);
        ps.setString(1, townName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        } else {
            return 0;
        }
    }

    private static String addTown(String townName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(ADD_TOWN_SQL);
        ps.setString(1, townName);
        ps.executeUpdate();

        return "Town " + townName + " was added to the database.";
    }

    private static void loadDriver(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver " + driver + " not found!");
        }
    }
}