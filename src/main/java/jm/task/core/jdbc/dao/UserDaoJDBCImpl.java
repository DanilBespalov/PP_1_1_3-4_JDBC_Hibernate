package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static int USER_COUNT;
    private Statement statement;

    {
        try {
            statement = Util.connection.createStatement();
            statement.execute("SET autocommit = 0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement.execute("CREATE TABLE users ('id' INT NOT NULL, 'name' VARCHAR(45) NOT NULL, 'lastName' VARCHAR(45) NOT NULL, 'age' INT NOT NULL, PRIMARY KEY('id'))");
            statement.execute("rollback ");
            statement.execute("commit ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            statement.execute("DROP TABLE users");
            statement.execute("rollback ");
            statement.execute("commit ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.execute("INSERT INTO users (name, lastName, age) VALUES(?, ?, ? )");
            statement.execute("name");
            statement.execute("lastName");
            statement.execute(String.valueOf(age));
            statement.execute("rollback ");
            statement.execute("commit ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try (PreparedStatement preparedStatement =
//                     Util.connection.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ? )")){
//
//            preparedStatement.setString(++USER_COUNT, name);
//            preparedStatement.setString(++USER_COUNT, lastName);
//            preparedStatement.setByte(++USER_COUNT, age);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void removeUserById(long id) {
        try {
            statement.execute(String.valueOf(id));
            statement.execute("rollback ");
            statement.execute("commit ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try (PreparedStatement preparedStatement =
//                     Util.connection.prepareStatement("DELETE FROM users WHERE id = ?")){
//            preparedStatement.setLong(USER_COUNT, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM users";
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

    public void cleanUsersTable() {
        try {
            statement.execute("TRUNCATE TABLE users");
            statement.execute("rollback ");
            statement.execute("commit ");
//        try (PreparedStatement preparedStatement =
//                     Util.connection.prepareStatement("TRUNCATE TABLE users")) {
//
//            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
