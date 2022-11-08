package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/database1.3";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";



    public static Connection connection;

    static {
        try {
            Class.forName("org.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM User";
            ResultSet result = statement.executeQuery(SQL);

            while(result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
