package Helpers;

import java.sql.*;

public class DBUtils {

    public static Connection getDBConnection() throws SQLException {

        // JDBS : mysql:// localhost:3306 /project

        String connectionString = "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + "/" + Config.dbName;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(connectionString,Config.dbUser,Config.dbPass);


    }
}
