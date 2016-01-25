package com.becomejavasenior.dao.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.jdbc.DaoFactory;
import com.becomejavasenior.jdbc.GenericDao;
import com.becomejavasenior.jdbc.PersistException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoFactoryImpl implements DaoFactory<Connection> {

    private String user = "postgres";
    private String password = "password";
    private String url = "jdbc:postgresql://localhost:5432/crmr_atlas";
    private String driver = "org.postgresql.Driver";
    private Map<Class, DaoFactory.DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public DaoFactoryImpl() {
        try {
            Class.forName(driver);//Регистрируем драйвер
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
