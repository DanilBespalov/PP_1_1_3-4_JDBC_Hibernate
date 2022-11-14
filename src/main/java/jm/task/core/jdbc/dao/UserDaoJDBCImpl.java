package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
//    private static int USER_COUNT;
    private static final Connection con = Util.connection;
    static {
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = con.createStatement()){
            statement.execute( "CREATE TABLE users (id long, name varchar(45) not null , lastName varchar(45) not null , age int not null)");
            statement.execute("commit ");
            statement.execute("rollback ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = con.createStatement()){
            statement.execute("DROP TABLE users");

            statement.execute("commit ");
            statement.execute("rollback ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement =
                     con.prepareStatement("INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)")){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User c именем " + name + "добавлен в базу данных");

            preparedStatement.execute("commit ");
            preparedStatement.execute("rollback ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                     con.prepareStatement("DELETE FROM users WHERE id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.execute("commit ");
            preparedStatement.execute("rollback ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = con.createStatement()){
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
            statement.execute("commit ");
            statement.execute("rollback ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = con.createStatement()){
            statement.execute("TRUNCATE TABLE users");

            statement.execute("commit ");
            statement.execute("rollback ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
