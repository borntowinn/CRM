package com.becomejavasenior.dao.jdbc.factory;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Default71721 on 15.02.16.
 */
public class DataSource {
    private static DataSource datasource;
    private BasicDataSource ds;

    private DataSource() {
        ds = new BasicDataSource();
        Properties props = new Properties();
        ClassLoader classLoader = DataSource.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("jdbc.properties")) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ds.setDriverClassName(props.getProperty("DRIVER"));
        ds.setUrl(props.getProperty("URL"));
        ds.addConnectionProperty("ssl", "true");
        ds.addConnectionProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        ds.setUsername(props.getProperty("USER"));
        ds.setPassword(props.getProperty("PASSWORD"));
    }

    public static DataSource getInstance() {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

}
