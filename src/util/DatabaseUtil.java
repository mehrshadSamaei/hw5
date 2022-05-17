package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private Connection connection;
    private String url;
    private String userName;
    private String password;


    public DatabaseUtil() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        this.url = "jdbc:mysql://localhost:3306/hw5db";
        this.userName = "root";
        this.password = "Mehrshad@1374";

        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        this.connection = DriverManager.getConnection(url, properties);

    }

    public Connection getConnection() {
        return connection;
    }
}
