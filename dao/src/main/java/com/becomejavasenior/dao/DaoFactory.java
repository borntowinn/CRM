package com.becomejavasenior.dao;

import java.sql.Connection;

public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }

    public Connection getContext() throws RuntimeException;
    public GenericDao getDao(Connection connection, Class dtoClass) throws RuntimeException;
}
