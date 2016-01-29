package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Company;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.jdbc.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CompanyDaoImpl extends AbstractJDBCDao<Company> implements CompanyDao {
    private final static String SELECT_QUERY = "SELECT company_id, company_name, phone_type, phone_number, email, web_site, createdby, address, isdeleted, creation_time FROM \"company\"";
    private final static String LAST_INSERT_ID_QUERY = "SELECT company_id, company_name, phone_type, phone_number, email, web_site, createdby, address, isdeleted, creation_time FROM \"company\" WHERE company_id=?";
    private final static String CREATE_QUERY = "INSERT INTO \"company\" (company_name, phone_type, phone_number, email, web_site, createdby, address, isdeleted, creation_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE \"company\" SET company_name = ?, phone_type = ?, phone_number = ?, email = ?, web_site = ?, createdby = ?, address = ?, isdeleted = ?, creation_time = ? WHERE company_id=?";
    private final static String DELETE_QUERY = "DELETE FROM \"company\" WHERE company_id= ?;";

    private UserDao userDao = DaoFactory.getUserDAO();

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getSelectPKQuery() {
        return LAST_INSERT_ID_QUERY;
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
    public Company create(Company company) throws PersistException {
        return persist(company);
    }

    @Override
    protected List<Company> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Company> result = new LinkedList<Company>();
        try {
            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setPhoneType(rs.getInt("phone_type"));
                company.setPhoneNumber(rs.getString("phone_number"));
                company.setEmail(rs.getString("email"));
                company.setWebsite(rs.getString("web_site"));
                company.setCreatedBy(userDao.getByPK(rs.getInt("createdby")));
                company.setAddress(rs.getString("address"));
                company.setDeleted(rs.getBoolean("isdeleted"));
                company.setCreationTime(rs.getDate("creation_time"));
                result.add(company);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Company object) throws PersistException {
        try {
            Date sqlDate = convert(object.getCreationTime());
            statement.setString(1, object.getCompanyName());
            statement.setInt(2, object.getPhoneType());
            statement.setString(3, object.getPhoneNumber());
            statement.setString(4, object.getEmail());
            statement.setString(5, object.getWebsite());
            statement.setInt(6, object.getCreatedBy().getId());
            statement.setString(7, object.getAddress());
            statement.setBoolean(8, object.getDeleted());
            statement.setDate(9, sqlDate);
            statement.setInt(10, object.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Company object) throws PersistException {
        try {
            Date sqlDate = convert(object.getCreationTime());
            int user_id = (object.getCreatedBy().getId() == null) ? new Integer(1) : object.getCreatedBy().getId();
            statement.setString(1, object.getCompanyName());
            statement.setInt(2, object.getPhoneType());
            statement.setString(3, object.getPhoneNumber());
            statement.setString(4, object.getEmail());
            statement.setString(5, object.getWebsite());
            statement.setInt(6, user_id);
            statement.setString(7, object.getAddress());
            statement.setBoolean(8, object.getDeleted());
            statement.setDate(9, sqlDate);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    protected Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }
}