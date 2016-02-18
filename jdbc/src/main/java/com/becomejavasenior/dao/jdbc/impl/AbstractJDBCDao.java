package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import org.apache.log4j.Logger;
import com.becomejavasenior.dao.jdbc.factory.DataSource;
import java.sql.*;
import java.util.List;

public abstract class AbstractJDBCDao<T> implements AbstractDao<T> {

    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);

    private Connection connection = ConnectionFactory.getConnection();

    protected DataSource dataSource = DataSource.getInstance();


    protected abstract String getSelectQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getCreateQuery();

    protected abstract String getSelectPKQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    private int addData(T object) {
        try (Connection connection = dataSource.getConnection()) {
            int lastInsertedId = 0;
            String sql = getCreateQuery();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            return lastInsertedId;
        } catch (SQLException e) {
            log.error("error while adding data: " + e.getMessage());
            throw new PersistException(e);
        }
    }

    @Override
    public T persist(T object) throws PersistException {
        int lastInsertedId = addData(object);
        return getByPK(lastInsertedId);
    }

    @Override
    public ResultSet executeQuery(String query) throws PersistException {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            /*SQLException will be ignored only for queries that don't produce a result set, for example INSERT or
            * UPDATE. In this case null value will be returned instead of a valid result set*/
            log.error("error executing query " + query + " " + e.getMessage());
            if (e.getErrorCode() != 0) {
                throw new PersistException();
            }
        }

        return null;
    }

    @Override
    public T getByPK(Integer id) throws PersistException {
        List<T> list;
        String sql = getSelectPKQuery();
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                log.warn("Record with PK = " + id + " not found.");
                throw new PersistException("Record with PK = " + id + " not found.");
            }

            if (list.size() > 1) {
                log.warn("Received more than one record.");
                throw new PersistException("Received more than one record.");
            }
        } catch (SQLException e) {
            log.error("couldn't get by PK " + e);
            throw new PersistException(e);
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            log.error("Couldn't get all entities " + e.getMessage());
            throw new PersistException(e);
        }
        return list;
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                log.error("On update modify more than 1 record: ");
                throw new PersistException("On update modify more than 1 record: " + count);
            }
        }
        catch (SQLException e) {
            log.error("error while updating " + e.getMessage());
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(Integer id) throws PersistException {
        String sql = getDeleteQuery();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            try {
                statement.setObject(1, id);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more than 1 record: " + count);
            }
        } catch (SQLException e) {
            log.error("couldn't delete " + e.getMessage());
            throw new PersistException(e);
        }
    }


    public void closeCurrentConnection()
    {
        try {
            connection.close();
        }
        catch (SQLException e)
        {
            log.error("couldn't close connection " + e.getMessage());
            throw new PersistException("Unable to close database connection");
        }
    }
}
