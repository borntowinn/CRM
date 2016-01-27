package com.becomejavasenior.dao.jdbc.factory;

import com.becomejavasenior.dao.jdbc.exception.PersistException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
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
}