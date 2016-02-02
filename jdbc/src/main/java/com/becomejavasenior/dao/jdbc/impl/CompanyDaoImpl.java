package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Company;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyDaoImpl extends AbstractJDBCDao<Company> implements CompanyDao<Company> {
    private final static String SELECT_QUERY = "SELECT company_id, company_name, phone_type, phone_number, email, web_site, createdby, address, isdeleted, creation_time FROM \"company\"";
    private final static String LAST_INSERT_ID_QUERY = "SELECT company_id, company_name, phone_type, phone_number, email, web_site, createdby, address, isdeleted, creation_time FROM \"company\" WHERE company_id=?";
    private final static String CREATE_QUERY = "INSERT INTO \"company\" (company_name, phone_type, phone_number, email, web_site, createdby, address, isdeleted, creation_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE \"company\" SET company_name = ?, phone_type = ?, phone_number = ?, email = ?, web_site = ?, createdby = ?, address = ?, isdeleted = ?, creation_time = ? WHERE company_id=?";
    private final static String DELETE_QUERY = "DELETE FROM \"company\" WHERE company_id= ?;";

    private UserDao<User> userDao = DaoFactory.getUserDAO();

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
                company.setCreationTime(rs.getTimestamp("creation_time").toLocalDateTime());
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
            prepareStatement(statement, object);
            statement.setInt(10, object.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Company object) throws PersistException {
        try {
            prepareStatement(statement, object);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    private void prepareStatement(PreparedStatement statement, Company object) throws SQLException {
        statement.setString(1, object.getCompanyName());
        statement.setInt(2, object.getPhoneType());
        statement.setString(3, object.getPhoneNumber());
        statement.setString(4, object.getEmail());
        statement.setString(5, object.getWebsite());
        statement.setInt(6, object.getCreatedBy().getId());
        statement.setString(7, object.getAddress());
        statement.setBoolean(8, object.getDeleted());
        statement.setTimestamp(9, Timestamp.valueOf(object.getCreationTime()));
    }
}