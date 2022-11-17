package jm.task.core.jdbc.util;


import java.sql.*;
public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/database1.3";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
