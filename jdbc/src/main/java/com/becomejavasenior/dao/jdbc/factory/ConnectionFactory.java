package com.becomejavasenior.dao.jdbc.factory;

import com.becomejavasenior.dao.exception.PersistException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection getConnection() {
        Properties props = new Properties();
        Connection connection;
        try (FileInputStream fis = new FileInputStream("C:\\Users\\iaroslav.borysov\\Desktop\\crm-atlas\\jdbc\\src\\main\\resources\\jdbc.properties")){
            props.load(fis);

            Class.forName(props.getProperty("DRIVER"));

            connection = DriverManager.getConnection(props.getProperty("URL"), props.getProperty("USER"), props.getProperty("PASSWORD"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }
}
