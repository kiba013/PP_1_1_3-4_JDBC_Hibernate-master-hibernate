package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
//        Util util = new Util();
//        util.getMyConnection();
//        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
//        user.createUsersTable();
//
//        user.saveUser("Name1", "LastName1", (byte) 20);
//        user.saveUser("Name2", "LastName2", (byte) 25);
//        user.saveUser("Name3", "LastName3", (byte) 31);
//        user.saveUser("Name4", "UserDaoJDBCImpl ", (byte) 38);
//
//        user.removeUserById(1);
//        user.getAllUsers();
//        user.cleanUsersTable();
//        user.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.createUsersTable();

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
