package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.exception.PersistException;
import org.apache.log4j.Logger;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Default71721 on 10.02.16.
 */
public class FileDaoImpl extends AbstractJDBCDao<File> implements FileDao<File> {


    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);

    private final static String SELECT_QUERY = "SELECT file_id, date_creation, file, file_name, company_id, contact_id, deal_id, user_id FROM file";
    private final static String SELECT_BY_PK_QUERY = "SELECT file_id, date_creation, file, file_name, company_id, contact_id, deal_id, user_id FROM file WHERE file_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO file (date_creation, file, file_name, company_id, contact_id, deal_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final static String UPDATE_QUERY = "UPDATE file SET date_creation= ?, file=?, file_name = ?, company_id = ?, contact_id = ?, deal_id = ?, user_id = ? WHERE file_id= ?;";
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
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        ContactDao<Contact> contactDao = DaoFactory.getContactDao();
        DealDao<Deal> dealDao = DaoFactory.getDealDao();
        UserDao<User> userDao = DaoFactory.getUserDAO();

        try {
            while (rs.next()) {
                File file = new File();
                file.setId(rs.getInt("file_id"));
                file.setCreationDate(rs.getTimestamp("date_creation").toLocalDateTime());
                file.setFile(rs.getBytes("file"));
                file.setFileName(rs.getString("file_name"));
                if(rs.getInt("company_id") != 0) {
                    file.setCompanyId(companyDao.getByPK(rs.getInt("company_id")));
                }
                if(rs.getInt("contact_id") != 0) {
                    file.setContactId(contactDao.getByPK(rs.getInt("contact_id")));
                }
                if(rs.getInt("deal_id") != 0) {
                    file.setDealId(dealDao.getByPK(rs.getInt("deal_id")));
                }
                if(rs.getInt("user_id") != 0) {
                    file.setUserId(userDao.getByPK(rs.getInt("user_id")));
                }
                result.add(file);
            }
        } catch (SQLException e) {
            log.error("result has not been parsed " + e);
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
            if (file.getCompanyId() != null) {
                statement.setInt(4, file.getCompanyId().getId());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            if (file.getContactId() != null) {
                statement.setInt(5, file.getContactId().getId());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            if (file.getDealId() != null)  {
                statement.setInt(6, file.getDealId().getId());
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (file.getUserId() != null) {
                statement.setInt(7, file.getUserId().getId());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
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
            statement.setInt(4, file.getCompanyId().getId());
            statement.setInt(5, file.getContactId().getId());
            statement.setInt(6, file.getDealId().getId());
            statement.setInt(7, file.getUserId().getId());
            statement.setInt(8, file.getId());
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Update File " + e);
            throw new PersistException(e);
        }
    }
}
