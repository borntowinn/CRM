package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.File;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.dao.FileDao;
import com.becomejavasenior.dao.exception.PersistException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Default71721 on 10.02.16.
 */
public class FileDaoImpl extends AbstractJDBCDao<File> implements FileDao<File> {

    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);

    private final static String SELECT_QUERY = "SELECT file_id, date_creation, file, file_name FROM file";
    private final static String SELECT_BY_PK_QUERY = "SELECT file_id, date_creation, file, file_name FROM file WHERE file_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO file (date_creation, file, file_name) VALUES (?, ?, ?);";
    private final static String UPDATE_QUERY = "UPDATE file SET date_creation= ?, file=?, file_name = ?  WHERE file_id= ?;";
    private final static String DELETE_QUERY = "DELETE FROM file WHERE file_id= ?;";

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getSelectPKQuery() {
        return SELECT_BY_PK_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    public File create(File file) throws PersistException {
        return persist(file);
    }

    @Override
    protected List<File> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<File> result = new ArrayList<>();
        try {
            while (rs.next()) {
                File file = new File();
                file.setId(rs.getInt("file_id"));
                file.setCreationDate(rs.getTimestamp("date_creation").toLocalDateTime());
                file.setFile(rs.getBytes("file"));
                file.setFileName(rs.getString("file_name"));
                result.add(file);
            }
        } catch (SQLException e) {
            log.error("result has not parsed " + e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, File file) throws PersistException {
        try {
            statement.setTimestamp(1, Timestamp.valueOf(file.getCreationDate()));
            statement.setBytes(2, file.getFile());
            statement.setString(3, file.getFileName());
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Insert File " + e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, File file) throws PersistException {
        try {
            statement.setTimestamp(1, Timestamp.valueOf(file.getCreationDate()));
            statement.setBytes(2, file.getFile());
            statement.setString(3, file.getFileName());
            statement.setInt(4, file.getId());
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Update File " + e);
            throw new PersistException(e);
        }
    }
}
