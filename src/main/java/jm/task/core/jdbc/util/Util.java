package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String user = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/company_repository";

     SessionFactory sessionFactory;

    public Connection getMyConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public  SessionFactory getMySessionFactory() {
        if (sessionFactory == null) {
            try {


                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/company_repository");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "root");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");

                prop.setProperty("hibernate.hbm2ddl.auto", "create");

                Configuration configuration = new Configuration();
                configuration.addProperties(prop);
                configuration.addAnnotatedClass(User.class);
                sessionFactory = configuration
                                .buildSessionFactory();


//                prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                prop.put(Environment.URL, "jdbc:mysql://localhost:3306/company_storage");
//                prop.put(Environment.USER, "root");
//                prop.put(Environment.PASS, "root");
//                prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//                prop.put(Environment.SHOW_SQL, "true");
//
//                prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//                prop.put(Environment.HBM2DDL_AUTO, "create-drop");
//
//                configuration.setProperties(prop);
//
//                configuration.addAnnotatedClass(User.class);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);


                System.out.println("good");
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
