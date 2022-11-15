package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
//    UserDao userDao = new UserDaoHibernateImpl();
//    SessionFactory factory = new Configuration()
//            .configure("hibernate.cfg.xml")
////            .addAnnotatedClass(User.class)
//            .buildSessionFactory();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        try (Session session = factory.getCurrentSession()) {
//            session.beginTransaction();
//            session.save(userDao);
//            session.getTransaction().commit();
//        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
