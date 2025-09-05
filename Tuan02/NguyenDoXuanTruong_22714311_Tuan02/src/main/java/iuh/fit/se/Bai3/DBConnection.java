package iuh.fit.se.Bai3;

import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // MariaDB
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/uploadfileservletdb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    static {
        try {
            // Load driver chỉ một lần duy nhất
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
