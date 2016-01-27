package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.dao.jdbc.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;

import java.sql.*;
import java.util.List;

public abstract class AbstractJDBCDao<T> {

    private Connection connection = ConnectionFactory.getConnection();

    protected abstract String getSelectQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getCreateQuery();
    protected abstract String getSelectPKQuery();
    protected abstract String getSelectLastInsertIdQuery();
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    private long addData(T object){
        long lastInsertedId = 0L;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                lastInsertedId = rs.getLong(1);
            }
            return lastInsertedId;
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    private T retrieveData(long lastInsertedId){
        T persistInstance;

        String sql = getSelectLastInsertIdQuery() + lastInsertedId;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return persistInstance;

    }

    public T persist(T object) throws PersistException {
        long lastInsertedId = addData(object);
        return retrieveData(lastInsertedId);
    }

    public T getByPK(Integer id) throws PersistException {
        List<T> list;
        String sql = getSelectPKQuery();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + id + " not found.");
        }

        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }

        return list.iterator().next();
    }

    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return list;
    }

    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        }
        catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    public void delete(Integer id) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, id);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
