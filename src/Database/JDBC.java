package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * used to handle sql connection
 */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//c868-do-user-2054280-0.b.db.ondigitalocean.com:25060/";
    private static final String databaseName = "defaultdb";
    private static String sslmode = "REQUIRED";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?ssl-mode="+sslmode; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "doadmin"; // Username
    private static String password = "0Unas9frulonuh3c"; // Password
    public static Connection connection;  // Connection Interface

    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
