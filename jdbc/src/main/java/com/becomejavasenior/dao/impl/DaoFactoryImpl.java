package com.becomejavasenior.dao.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.DaoFactory;
import com.becomejavasenior.dao.GenericDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory {

    private static String USER;
    private static String PASSWORD;
    private static String URL;
    private static String DRIVER;
    private Map<Class, DaoFactory.DaoCreator> creators;

     static {
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("jdbc\\src\\main\\resources\\jdbc.properties");
            props.load(fis);

            USER = props.getProperty("USER");
            URL = props.getProperty("URL");
            PASSWORD = props.getProperty("PASSWORD");
            DRIVER = props.getProperty("DRIVER");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoFactory.DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public DaoFactoryImpl() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(UserRole.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new UserRoleDaoImpl(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new UserDaoImpl(connection);
            }
        });
    }
}
