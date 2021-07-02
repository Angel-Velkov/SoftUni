import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Demo {

    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/soft_uni";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;

        String password = scanner.nextLine();
        password = password.equals("") ? "1234" : password;

        // 1. Load DB Driver
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            System.out.printf("Database driver: '%s' not found!%n", DB_Driver);
            System.exit(0);
        }
        System.out.println("DB Driver loaded successfully!");

        // 2. Connect to DB
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, props);
        } catch (SQLException e) {
            System.out.printf("Can not connect to DB: '%s'.%n", DB_URL);
            System.exit(0);
        }
        System.out.printf("DB connection created successfully: '%s'.%n", DB_URL);

        // 3. Read query params
        System.out.println("Enter minimal salary (<Enter> for '40000'): ");
        String salaryStr = scanner.nextLine().trim();
        salaryStr = salaryStr.length() > 0 ? "40000" : salaryStr;
        double salary = 40_000;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            System.err.printf("Invalid number: '%s'!", salaryStr);
        }

        // 4. Create prepared statement
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        // 5. Execute prepared statement with parameter
            ps.setDouble(1, salary);
            ResultSet rs = ps.executeQuery();
        // 6. Print result
            System.out.println("|     id     |    first_name   |    last_name    |   salary   |");
            while (rs.next()) {
                System.out.printf("| %10d | %-15.15s | %-15.15s | %10.2f |%n",
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDouble("salary")
                );
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
        // 7. Close connection
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.printf("Error closing DB connection %s", e.getMessage());
            }
        }
    }
}
