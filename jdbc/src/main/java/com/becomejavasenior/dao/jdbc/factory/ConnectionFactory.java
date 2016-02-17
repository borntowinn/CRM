package com.becomejavasenior.dao.jdbc.factory;

import com.becomejavasenior.dao.exception.PersistException;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {
        Properties props = new Properties();
        Connection connection;
        ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("jdbc.properties");){
            props.load(inputStream);
            Class.forName(props.getProperty("DRIVER"));

            connection = DriverManager.getConnection(props.getProperty("URL"), props.getProperty("USER"), props.getProperty("PASSWORD"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            log.error("connect to DB failed " + e.getMessage());
            throw new PersistException(e);
        }
        return connection;
    }
}
