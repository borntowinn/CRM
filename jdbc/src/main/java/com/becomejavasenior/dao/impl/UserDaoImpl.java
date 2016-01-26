package com.becomejavasenior.dao.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.GenericDao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class UserDaoImpl extends AbstractJDBCDao<User> {

    private final static String SELECT_QUERY = "SELECT user_id, name, password, description, date_creation, email, mobile_phone, work_phone, user_role_id, language FROM \"user\"";
    private final static String LAST_INSERT_QUERY = "SELECT user_id, name, password, description, date_creation, email, mobile_phone, work_phone, user_role_id, language FROM \"user\" WHERE user_id=";
    private final static String LAST_INSERT_ID_QUERY = "SELECT user_id, name, password, description, date_creation, email, mobile_phone, work_phone, user_role_id, language FROM \"user\" WHERE user_id=?";
    private final static String CREATE_QUERY = "INSERT INTO \"user\" (name, password, description, date_creation, email, mobile_phone, work_phone, user_role_id, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE \"user\" SET name = ?, password = ?, description  = ?, date_creation = ?, email = ?, mobile_phone = ?, work_phone = ?, user_role_id = ?, language = ? WHERE user_id=?";
    private final static String DELETE_QUERY = "DELETE FROM \"user\" WHERE user_id= ?;";

    private GenericDao<UserRole> userRoleDao = null;

    public UserDaoImpl(Connection connection) {
        super(connection);
        userRoleDao = new UserRoleDaoImpl(connection);
    }

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getSelectLastInsertIdQuery() {
        return LAST_INSERT_QUERY;
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
    public User create(User user) throws PersistException {
        return persist(user);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        UserRole userRole = null;
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setDescription(rs.getString("description"));
                user.setCreationDate(rs.getDate("date_creation"));
                user.setEmail(rs.getString("email"));
                user.setMobilePhone(rs.getString("mobile_phone"));
                user.setWorkPhone(rs.getString("work_phone"));
                userRole = userRoleDao.getByPK(rs.getInt("user_role_id"));
                user.setUserRole(userRole);
                user.setLanguage(rs.getInt("language"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            Date sqlDate = convert(object.getCreationDate());

            statement.setString(1, object.getName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getDescription());
            statement.setDate(4, sqlDate);
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getMobilePhone());
            statement.setString(7, object.getWorkPhone());
            statement.setInt(8, object.getUserRole().getId());
            statement.setInt(9, object.getLanguage());
            statement.setInt(10, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {

            Date sqlDate = convert(object.getCreationDate());
            int user_role_id = (object.getUserRole().getId() == null) ? new Integer(1) : object.getUserRole().getId();

            statement.setString(1, object.getName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getDescription());
            statement.setDate(4, sqlDate);
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getMobilePhone());
            statement.setString(7, object.getWorkPhone());
            statement.setInt(8, user_role_id);
            statement.setInt(9, object.getLanguage());

        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
