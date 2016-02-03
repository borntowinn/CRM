package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by valkos on 02.02.16.
 */
public class ContactDaoImpl extends AbstractJDBCDao<Contact> implements ContactDao<Contact> {
    private static final String SELECT_QUERY = "SELECT contact_id, name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id  FROM contact";
    private static final String SELECT_BY_PK_QUERY = "SELECT contact_id, name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id  FROM contact WHERE contact_id = ?";
    private static final String CREATE_QUERY = "INSERT INTO contact (name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE contact SET name_surname = ?, phone_type = ?, phone_number  = ?, email = ?, skype = ?, position = ?, isdeleted = ?, creation_time = ?, createdBy = ?, company_id = ?  WHERE contact_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM contact WHERE contact_id = ?";

    private UserDao<User> userDao = DaoFactory.getUserDAO();
    private CompanyDao<Company> companyDAO = DaoFactory.getCompanyDAO();

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectPKQuery() {
        return SELECT_BY_PK_QUERY;
    }

    @Override
    protected List<Contact> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Contact> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("contact_id"));
                contact.setNameSurname(rs.getString("name_surname"));
                contact.setPhoneType(rs.getInt("phone_type"));
                contact.setPhoneNumber(rs.getString("phone_number"));
                contact.setEmail(rs.getString("email"));
                contact.setSkype(rs.getString("skype"));
                contact.setPosition(rs.getString("position"));
                contact.setDeleted(rs.getBoolean("isdeleted"));
                contact.setCreationTime(rs.getTimestamp("creation_time").toLocalDateTime());
                contact.setCreatedBy(userDao.getByPK(rs.getInt("createdby")));
                contact.setCompanyId(companyDAO.getByPK(rs.getInt("company_id")));
                result.add(contact);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Contact contact) throws PersistException {
        try {
            int user_id = (contact.getCreatedBy().getId() == null) ? new Integer(1) : contact.getCreatedBy().getId();
            int company_id = (contact.getCompanyId().getId() == null) ? new Integer(1) : contact.getCompanyId().getId();
            statement.setString(1, contact.getNameSurname());
            statement.setInt(2, contact.getPhoneType());
            statement.setString(3, contact.getPhoneNumber());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getSkype());
            statement.setString(6, contact.getPosition());
            statement.setBoolean(7, contact.getDeleted());
            statement.setTimestamp(8, Timestamp.valueOf(contact.getCreationTime()));
            statement.setInt(9, user_id);
            statement.setInt(10, company_id);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Contact contact) throws PersistException {
        try {
            statement.setString(1, contact.getNameSurname());
            statement.setInt(2, contact.getPhoneType());
            statement.setString(3, contact.getPhoneNumber());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getSkype());
            statement.setString(6, contact.getPosition());
            statement.setBoolean(7, contact.getDeleted());
            statement.setTimestamp(8, Timestamp.valueOf(contact.getCreationTime()));
            statement.setInt(9, contact.getCreatedBy().getId());
            statement.setInt(10, contact.getCompanyId().getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Contact create(Contact contact) throws PersistException {
        return persist(contact);
    }
}
