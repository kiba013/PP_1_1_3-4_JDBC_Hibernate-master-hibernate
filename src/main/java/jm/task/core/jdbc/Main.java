package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 20);
        userDaoHibernate.saveUser("Name2", "LastName2", (byte) 21);
        userDaoHibernate.saveUser("Name3", "LastName3", (byte) 22);
        userDaoHibernate.saveUser("Name4", "LastName4", (byte) 23);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.removeUserById(3);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.getAllUsers();
    }
}
