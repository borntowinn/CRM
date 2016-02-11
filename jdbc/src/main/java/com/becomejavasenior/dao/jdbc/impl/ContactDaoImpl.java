package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by valkos on 02.02.16.
 */
public class ContactDaoImpl extends AbstractJDBCDao<Contact> implements ContactDao<Contact> {

    private final String SELECT_QUERY = "SELECT contact_id, name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id  FROM contact";
    private final String SELECT_BY_PK_QUERY = "SELECT contact_id, responsible, name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id  FROM contact WHERE contact_id = ?";
    private final String CREATE_QUERY = "INSERT INTO contact (name_surname, phone_type, phone_number, email, skype, position, isDeleted, creation_time, createdBy, company_id, responsible) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE contact SET name_surname = ?, phone_type = ?, phone_number  = ?, email = ?, skype = ?, position = ?, isdeleted = ?, creation_time = ?, createdBy = ?, company_id = ?  WHERE contact_id = ?;";
    private final String DELETE_QUERY = "DELETE FROM contact WHERE contact_id = ?";
    private final String ADD_COMMENT_QUERY = "INSERT INTO comment (comment, data_creation) VALUES (?, ?)";
    private final String ADD_COMMENT_TO_CONTACT_QUERY = "INSERT INTO comment_to_contact (comment_id, contact_id) VALUES (?, ?)";


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
                contact.setResponsible(userDao.getByPK(rs.getInt("responsible")));
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
            throw new PersistException(e);
        }
    }

    @Override
    public Contact create(Contact contact) throws PersistException {
        return persist(contact);
    }

    @Override
    public void addCommentToContact(Comment comment, int contact_id) {
        int commentInsertedId = 0;
        try (PreparedStatement statement = super.getConnection().prepareStatement(ADD_COMMENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comment.getComment());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                commentInsertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        try (PreparedStatement statement = super.getConnection().prepareStatement(ADD_COMMENT_TO_CONTACT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, commentInsertedId);
            statement.setInt(2, contact_id);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                commentInsertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }


    }

    @Override
    public void addFileToContact() {

    }
}
