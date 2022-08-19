package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;

import javax.persistence.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = util.getMySessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String sql = "Create table User (id Int PRIMARY KEY AUTO_INCREMENT, name varchar(128), lastName varchar(128), age int)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Table 'User' was created");
        } catch (Exception e) {
            System.out.println("Table 'User' already exists");
        }
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = util.getMySessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "DROP TABLE  User";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Table 'User' was deleted");
        } catch (SQLGrammarException ex) {
            ex.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = util.getMySessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
            System.out.println("User: " + name + " successfully added to Table 'User'");
        } catch (HibernateException ex) {
            if (transaction != null) transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try (Session session = util.getMySessionFactory().openSession();) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User by ID: " + " was deleted successfully!");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = util.getMySessionFactory().openSession()) {
            System.out.println(session.createQuery("from User", User.class).list());
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getMySessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "Truncate Table User";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
