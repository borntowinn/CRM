package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.dao.exception.PersistException;
import org.apache.log4j.Logger;
import com.becomejavasenior.dao.jdbc.factory.DataSource;
import java.sql.*;
import java.util.List;

public abstract class AbstractJDBCDao<T> implements AbstractDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractJDBCDao.class);

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
            LOGGER.error("error while adding data: " + e.getMessage());
            throw new PersistException(e);
        }
    }

    @Override
    public T persist(T object) throws PersistException {
        int lastInsertedId = addData(object);
        return getByPK(lastInsertedId);
    }

    @Override
    public ResultSet executeQuery(PreparedStatement statement) {
        try {
            int count = statement.executeUpdate();
            if (count != 1)
                throw new PersistException("On persist modify more then 1 record: " + count);
            return statement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.error("can't add data to table" + e);
            throw new PersistException(e);
        }
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
                LOGGER.warn("Record with PK = " + id + " not found.");
                throw new PersistException("Record with PK = " + id + " not found.");
            }

            if (list.size() > 1) {
                LOGGER.warn("Received more than one record.");
                throw new PersistException("Received more than one record.");
            }
        } catch (SQLException e) {
            LOGGER.error("couldn't get by PK " + e);
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
            LOGGER.error("Couldn't get all entities " + e.getMessage());
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
                LOGGER.error("On update modify more than 1 record: ");
                throw new PersistException("On update modify more than 1 record: " + count);
            }
        }
        catch (SQLException e) {
            LOGGER.error("error while updating " + e.getMessage());
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
            LOGGER.error("couldn't delete " + e.getMessage());
            throw new PersistException(e);
        }
    }

    public List<?> selectEntityByParamId(int id, String sql) {
        List<?> list = null;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            list = parseResultSet(set);
            if (list == null || list.size() == 0) {
                LOGGER.warn("Record with PK = " + id + " in Comment table not found.");
                throw new PersistException("Record with PK = " + id + " in Comment table not found.");
            }
        } catch (SQLException e) {
            LOGGER.error("Couldn't select comments for entity with id " + id + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
