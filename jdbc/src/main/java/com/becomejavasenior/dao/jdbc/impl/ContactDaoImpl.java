package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by valkos on 02.02.16.
 */
public class ContactDaoImpl extends AbstractJDBCDao<Contact> implements ContactDao<Contact> {

    private static final Logger log = Logger.getLogger(ContactDaoImpl.class);

    private static final String SELECT_QUERY = "SELECT contact_id, name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id, responsible  FROM contact";
    private static final String SELECT_BY_PK_QUERY = "SELECT contact_id, name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id, responsible  FROM contact WHERE contact_id = ?";
    private static final String CREATE_QUERY = "INSERT INTO contact (name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id, responsible) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE contact SET name_surname = ?, phone_type = ?, phone_number  = ?, email = ?, skype = ?, position = ?, isdeleted = ?, creation_time = ?, createdBy = ?, company_id = ?, responsible = ?  WHERE contact_id = ?;";
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
        ArrayList<Contact> result = new ArrayList<>();
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
                contact.setResponsible(userDao.getByPK(rs.getInt("responsible")));
                result.add(contact);
            }
        } catch (SQLException e) {
            log.error("result has not parsed " + e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Contact contact) throws PersistException {
        try {
            int user_id = (contact.getCreatedBy().getId() == null) ? new Integer(1) : contact.getCreatedBy().getId();
            int responsible_id = (contact.getResponsible().getId() == null) ? new Integer(1) : contact.getResponsible().getId();
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
            statement.setInt(11, responsible_id);
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Insert Comment " + e);
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
            statement.setInt(11, contact.getResponsible().getId());
            statement.setInt(12, contact.getId());
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Update Comment " + e);
            throw new PersistException(e);
        }
    }

    @Override
    public Contact create(Contact contact) throws PersistException {
        return persist(contact);
    }
}
