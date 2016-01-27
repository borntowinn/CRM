package com.becomejavasenior.dao.impl;

import com.becomejavasenior.dao.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactoryImpl {

    public static Connection getConnection() {
        Properties props = new Properties();
        FileInputStream fis = null;
        Connection connection = null;
        try {
            fis = new FileInputStream("src\\main\\resources\\jdbc.properties");
            props.load(fis);
            
            Class.forName(props.getProperty("DRIVER"));
            
            connection = DriverManager.getConnection(props.getProperty("URL"), props.getProperty("USER"), props.getProperty("PASSWORD"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    public static UserRoleDao getUserRoleDAO() {
        return new UserRoleDaoImpl();
    }

    public static UserDao getUserDAO() {
        return new UserDaoImpl();
    }
}
