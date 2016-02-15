package com.becomejavasenior.dao.jdbc.factory;

import com.becomejavasenior.dao.exception.PersistException;

import javax.annotation.Resource;
import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static DataSource dataSource;

    public static Connection getConnection() {
        try {
            dataSource = DataSource.getInstance();
            return dataSource.getConnection();
        } catch (SQLException sqlException) {
            throw new PersistException(sqlException.getMessage());
        }
    }
}
